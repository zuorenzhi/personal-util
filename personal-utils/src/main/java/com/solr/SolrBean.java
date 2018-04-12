package com.solr;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * @see http://blog.csdn.net/gisredevelopment/article/details/50443458
 * @notice [百度云盘:\workspace-solr\solr_5.5.4_可启动服务器\]
 * <p>Description: [描述该类概要功能介绍]</p>
 * Created on 2017年3月1日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class SolrBean {


    static void query(SolrClient solrClient){
        SolrQuery query = new SolrQuery();
//          query.setQuery("username:*u*");
        query.setQuery("name:hello , Mr zuo");

        QueryResponse response = null;
        try {

            response = solrClient.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SolrDocumentList documentList = response.getResults();

        for(int i=0;i<documentList.size();i++){
            Collection<String> list=  documentList.get(i).getFieldNames();
            for(String s:list){
//                System.out.println(documentList.get(i).get(s));
                System.out.printf("%s的值是%s%n",s,documentList.get(i).get(s));
            }
        }
    }

    static void add(SolrClient solrClient){
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "552199");
        document.addField("name", "hello , Mr zuo");
        document.addField("price", "49.99");
        document.addField("username", "zuorenzhi");
        try {
            UpdateResponse response = solrClient.add(document);

            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void delete(SolrClient solrClient){
        try {
            solrClient.deleteByQuery("name:hello , Mr zuo");
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Expected mime type application/octet-stream but got text/html
    public static void main(String []args){
        String urlString = "http://localhost:8080/solr/jobs";
        SolrClient solr = new HttpSolrClient(urlString);

        add(solr);
        query((solr));

//        delete(solr);
    }
}