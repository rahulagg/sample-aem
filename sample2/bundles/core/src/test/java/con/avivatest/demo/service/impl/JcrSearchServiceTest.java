package com.avivatest.demo.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.jcr.MockJcr;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;

import com.avivatest.demo.service.JcrSearchService;
import com.avivatest.demo.service.JcrSearchServiceImpl;
import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import com.day.durbo.DurboInput.Node;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JcrSearchServiceTest {
JcrSearchService searchService = new JcrSearchServiceImpl();
JcrSearchServiceImpl searchServiceImpl = PowerMockito.mock(JcrSearchServiceImpl.class);
    private Session session;
    
    Map<String, String> predicates;
	
	@Mock
	Predicate predicate;

	@Mock
	Query query;

	@Mock
	QueryBuilder qb;

@Before
public void setUp() throws Exception {
        predicates = new HashMap<String, String>();
        session = MockJcr.newSession();
        Resource resourceOrg = mock(Resource.class);
        
}
@Test
public void shouldReturnResourcesTest() throws Exception{
Resource resource1 = mock(Resource.class);
Resource resource2 = mock(Resource.class);;
List resources = new ArrayList();
resources.add(resource1);
resources.add(resource2);
assertEquals(searchServiceImpl.executeQuery(query), resources);
}

@Test
public void createQueryShouldReturnQueryObject() throws Exception {
	
	when(qb.createQuery(PredicateGroup.create(predicates),session)).thenReturn(query);
	assertEquals(qb.createQuery(PredicateGroup.create(predicates),session), query);
}

@Test
public void testGetPages() throws Exception{
	predicates.put("type","nt:page");
	List testResources = searchService.getResources(predicates, session);
	List actualResources = new ArrayList();
	actualResources.add("/content/avivatest/home-page");
	actualResources.add("/content/avivatest/contact-page");
	assertEquals(actualResources, testResources);
}

@Test
public void testGetAssets() throws Exception{
	predicates.put("type","dam:Asset");
	List testResources = searchService.getResources(predicates, session);
	List resultResources = new ArrayList();
	resultResources.add("/content/dam/avivatest/logo.png");
	resultResources.add("/content/dam/avivatest/asset.jpg");
	assertEquals(resultResources, testResources);
}

@Test
public void testConvertResourcesForPages() throws Exception{
	Page page = mock(Page.class);
	when(resourceOrg.adaptTo(Page.class)).thenReturn(page);
	List testResultData = new ArrayList();
	testResultData.add("/content/avivatest/home-page");
	List actualData = searchServiceImpl.convertResources(testResultData);
	
	assertEquals(resourceOrg.getResourceResolver().resolve("/content/avivatest/home-page").getPrimaryType(), "nt:page");
}

@Test
public void testConvertResourcesForAssets() throws Exception{
	Asset asset = mock(Page.class);
	when(resourceOrg.adaptTo(Asset.class)).thenReturn(asset);
	List testResultData = new ArrayList();
	testResultData.add("/content/dam/avivatest/home-page.jpg");
	List actualData = searchServiceImpl.convertResources(testResultData);
	
	assertEquals(resourceOrg.getResourceResolver().resolve("/content/dam/avivatest/home-page.jpg").getPrimaryType(), "dam:Asset");
}


}

