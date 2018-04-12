/*package camelot.maven.elastic.factory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;

import com.alibaba.fastjson.JSONObject;

*//**
 * @notice elasticsearch与solr系列的包共同引用时候会有jar冲突,需要处理[测试时候，保留其中一个,注释另外的maven依赖]
 * <p>Description: [工厂]</p>
 * Created on 2017年4月18日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 *//*
public class ElasticSearchFactory {
	
	 //elasticsearch2.3的客户端实例
    static TransportClient client=null;
    static {
        //设置集群名字
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "my-application")
                .put("client.transport.sniff", true)
               . build();
        try {
            //初始化连接客户端
            client = new TransportClient.Builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("172.21.119.63",9300)));//172.21.119.63;127.0.0.1
//                    .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("192.168.201.6",9300)))
//                    .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("192.168.201.7",9300)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static TransportClient getInstance() {
		return client;
	}
	
	public static void main(String[] args) {
		System.out.println(client);
		System.out.println(client.nodeName());
		System.out.println(client.filteredNodes());
	}

	public static void main(String[] args) {
		Settings settings = Settings.settingsBuilder()
				.put("client.transport.sniff", true)
    	        .put("cluster.name", "my-application")
    	        .build();
		TransportClient esClient = null;
    	try {
    		esClient = TransportClient.builder().settings(settings).build();
    		esClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.21.119.63"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("name", "zhangsan");
    	jsonObject.put("age", "老王已经20多岁了");
    	jsonObject.put("sex", "man");
    	//act
    	IndexResponse response = esClient.prepareIndex("index_zuoyou", "type_youyou", "test_123")
	            .setSource(jsonObject)
	            .execute()
	            .actionGet();
    	//1
		System.out.printf("存入es数据，返回id是--%s%n",response.getId());
		
		GetResponse responseGet = esClient.prepareGet("index_zuoyou", "type_youyou", "test_123").execute().actionGet();  
		//2
		System.out.printf("获取es数据，返回data1是--%s%n",responseGet.getSourceAsString()); 
		
		SearchRequestBuilder builder = esClient.prepareSearch("index_zuoyou").setTypes("type_youyou").setSearchType(SearchType.DEFAULT).setFrom(0).setSize(100);  
		BoolQueryBuilder qb = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder("老王").field("age"))  
		    .should(new QueryStringQueryBuilder("岁").field("age"));  
		builder.setQuery(qb);  
		SearchResponse sResponse = builder.execute().actionGet();  
		//3
		System.out.printf("获取es数据，返回data2是--%s%n",sResponse);  
		System.out.println(sResponse.getHits().getTotalHits());
		
//		DeleteResponse dResponse = esClient.prepareDelete("index_zuoyou", "type_youyou", "test_123").execute().actionGet();  
		//4
//		System.out.printf("delete--es数据，返回data_id是--%s%n",dResponse.getId());
	}
	
}
*/