package com.avivatest.demo.service;

import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;

import com.day.cq.dam.api.Asset;
import com.day.cq.wcm.api.Page;

/**
 * Jcr Search Service
 *
 * This service allows users to getResources for
 */
public interface JcrSearchService {

    /**
     * Get a list of resources based on search predicates passed in
     *
     * @param predicates the search predicates
     * @param session the session used to traverse the repository
     * @return a list of resources matching the predicates
     */
    List<Resource> getResources(Map<String, String> predicates, Session session);

    /**
     * Get a list of resources based on search predicates passed in
     *
     * @param predicates the search predicates
     * @param session the session used to traverse the repository
     * @return a list of pages matching the predicates
     */
    List<Page> getPages(Map<String, String> predicates, Session session);

    /**
     * Get a list of assets based on search predicates passed in
     *
     * @param predicates the search predicates
     * @param session the session used to traverse the repository
     * @return a list of assets matching the predicates
     */
    List<Asset> getAssets(Map<String, String> predicates, Session session);
}
