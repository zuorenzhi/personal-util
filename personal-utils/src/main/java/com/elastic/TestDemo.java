/*package com.camelot.maven.elastic;

import java.io.IOException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.camelot.maven.domain.Student;
import com.camelot.maven.elastic.factory.ElasticSearchFactory;

public class TestDemo {

	
	@Test
	public void assembleData() throws  IOException{
		
		TransportClient client = ElasticSearchFactory.getInstance();
		//1.jsonBuilder
		IndexResponse response = client.prepareIndex("comment_index", "comment_ugc", "comment_123674")  
			    .setSource( XContentFactory.jsonBuilder()  
			    .startObject()  
			      .field("author", "569874")  
			      .field("author_name", "riching")  
			      .field("mark", 232)  
			      .field("body", "北京不错，但是人太多了--么么哒")  
			      .field("createDate", "20130801175520")  
			      .field("valid", true)  
			    .endObject())  
			    .setTTL(8000)  
			    .execute().actionGet();  
			  
			System.out.println(response.getId()); 
			
		 //2.object
			Student student = new Student(103161066, 20, "riching", "beijing");  
			String jsonValue = JSON.toJSONString(student);  
			response = client.prepareIndex("student_index", "student_info", "stu_103161066").setSource(jsonValue).execute().actionGet();  
			System.out.println(response.getId());  
	}
	
	@Test
	public void getDataById(){
		Client client = ElasticSearchFactory.getInstance();
		GetResponse responseGet = client.prepareGet("comment_index", "comment_ugc","comment_123674").execute().actionGet();  
		System.out.println(responseGet.getSourceAsString()); 
	}
	
	//查询索引
	@Test
	public void queryIndex(){
		Client client = ElasticSearchFactory.getInstance();
		SearchRequestBuilder builder = client.prepareSearch("comment_index").setTypes("comment_ugc").setSearchType(SearchType.DEFAULT).setFrom(0).setSize(100);  
		BoolQueryBuilder qb = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder("北京").field("body"))  
		    .should(new QueryStringQueryBuilder("太多").field("body"));  
		builder.setQuery(qb);  
		SearchResponse response = builder.execute().actionGet();  
		System.out.println("  " + response);  
		System.out.println(response.getHits().getTotalHits()); 
	}
	
	@Test
	public void deleteIndex(){
		Client client = ElasticSearchFactory.getInstance();
//		DeleteResponse response = client.prepareDelete("comment_index", "comment_ugc", "comment_123674") .setOperationThreaded(false).execute().actionGet();  
//		System.out.println(response.getId());  
	}
	
}
*/