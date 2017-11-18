package com.avivatest.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;

import com.day.cq.dam.api.Asset;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.api.Page;


/**
 * {@inheritDoc}
 */
@Service
@Component
public class JcrSearchServiceImpl implements JcrSearchService {

    private static final String TYPE_PARAM = "TYPE";

    @Reference
    private QueryBuilder queryBuilder;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Resource> getResources(Map<String, String> predicates, Session session) {

        // create the query
        Query query = queryBuilder.createQuery(PredicateGroup.create(predicates), session);

        // execute the query
        return executeQuery(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Page> getPages(Map<String, String> predicates, Session session) {
        predicates.put(TYPE_PARAM, NameConstants.NT_PAGE);
        List<Resource> resources = getResources(predicates, session);

        return convertResources(resources, Page.class);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<Asset> getAssets(Map<String, String> predicates, Session session) {
        predicates.put(TYPE_PARAM, NameConstants.NT_DAM_ASSET);
        List<Resource> resources = getResources(predicates, session);

        return convertResources(resources, Asset.class);
    }

    private static List<Resource> executeQuery(Query query) {
        List<Resource> results = new ArrayList<>();
        SearchResult searchResult = query.getResult();

        Iterator<Resource> resources = searchResult.getResources();

        resources.forEachRemaining(results::add);

        return results;
    }

    private static <T> List<T> convertResources(List<Resource> resources, Class<T> classType) {
        List<T> results = new ArrayList<>();

        resources.forEach(resource -> {
            T element = resource.adaptTo(classType);

            if (element != null) {
                results.add(element);
            }
        });

        return results;
    }
}
