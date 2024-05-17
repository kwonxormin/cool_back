package com.cool.scheduler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KpiConfig {
    // @Autowired
    // CoolService coolService;

    // @Scheduled(cron = "15 32 15 * * *") // KPI L1 매일 23시 59분 59초에 실행
    // public void kpi_test_3() throws Exception{
    // // String[] kpiCertKeyList =
    // {"fe6b-b500-ee55-0727","de87-ec6b-d39d-d746","027d-6cf3-eed2-e54b","9ac8-ca1e-6ae3-e91d"};

    // String[] kpiData = {"8", "8", "8", "5", "5", "5", "6", "6", "6", "8", "8",
    // "8", "10", "10", "10", "12", "12", "12", "3", "3", "3", "4", "4", "4", "5",
    // "5", "5", "6", "6", "6", "8", "8", "8", "5", "5", "5", "7", "7", "7", "8",
    // "8", "8", "9", "9", "9", "9", "9", "9", "4", "4", "4", "7", "7", "7", "8",
    // "8", "8", "8", "8", "8", "8", "8", "8", "1", "1", "1", "3", "3", "3", "1",
    // "1", "1", "1", "1", "1", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3",
    // "3", "3", "3", "3", "3", "1", "1", "1", "1", "1", "1", "3", "3", "3", "1",
    // "1", "1", "2", "2", "2", "1", "1", "1", "1", "1", "1", "3", "3", "3", "1",
    // "1", "1", "1", "1", "1", "3", "3", "3", "3", "3", "3", "3"};

    // StringBuffer response = new StringBuffer();

    // SimpleDateFormat format1 = new SimpleDateFormat ("YYYYMMddHHmmss");
    // Date time = new Date();

    // JSONObject jsonObject1 = new JSONObject();
    // JSONArray jsonArray1 = new JSONArray();
    // JSONObject finalJsonObject = new JSONObject();

    // for(int i=0;i<kpiData.length;i++){
    // jsonObject1 = new JSONObject();
    // jsonArray1 = new JSONArray();
    // finalJsonObject = new JSONObject();

    // jsonObject1.put("kpiCertKey", "9ac8-ca1e-6ae3-e91d");
    // jsonObject1.put("ocrDttm", "20240229");
    // jsonObject1.put("kpiFldCd", "P");
    // jsonObject1.put("kpiDtlCd", "B");
    // jsonObject1.put("kpiDtlNm", "생산량증가");
    // jsonObject1.put("msmtVl", String.valueOf(kpiData[i]));
    // jsonObject1.put("unt", "건");
    // jsonObject1.put("trsDttm", format1.format(time));
    // jsonArray1.add(jsonObject1);

    // finalJsonObject.put("KPILEVEL3", jsonArray1);

    // System.out.println(finalJsonObject);

    // String apiURL = "http://www.ssf-kpi.kr:8080/kpiLv3/kpiLv3Insert";
    // URL url = new URL(apiURL);
    // HttpURLConnection con = (HttpURLConnection)url.openConnection();
    // con.setRequestMethod("POST");
    // con.setRequestProperty("Content-type", "application/json;UTF-8");
    // //con.setRequestProperty("Accept-Charset", "UTF-8");
    // con.setDoOutput(true);

    // DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    // wr.write(finalJsonObject.toString().getBytes("UTF-8"));
    // wr.flush();
    // wr.close();
    // int responseCode = con.getResponseCode();

    // BufferedReader br;
    // if(responseCode==200) { // 정상 호출
    // br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
    // } else { // 에러 발생
    // br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
    // }
    // String inputLine;
    // response = new StringBuffer();
    // while ((inputLine = br.readLine()) != null) {
    // response.append(inputLine);
    // }
    // br.close();
    // System.out.println(response.toString());
    // }
    // }

    // @Scheduled(cron = "55 59 23 * * *") // KPI L1 매일 23시 59분 59초에 실행
    // public void kpi_level_1() throws Exception {
    //     String[] kpiCertKeyList = { "3e3e-aada-3728-0cc7", "d283-6aa2-06d5-57f4", "b896-2e86-9cd0-ba77",
    //             "6096-23ae-655d-41d6", "b78b-ac4c-308d-b8e0",
    //             "45d8-b84d-af15-6a05", "0cbf-f455-5c7d-ffb4", "4c4c-2717-a2ec-1212", "2ca3-ed03-8c8d-f483",
    //             "f736-869c-5bd0-f0a0" };
    //     // String[] kpiCertKeyList2 =
    //     // {"580-87-00924","502-28-16002","616-13-36910","616-26-97858","856-10-00789","137-05-70161","606-81-66231","131-23-40533","412-08-30135","165-81-02003"};
    //     // String[] kpiCertKeyList3 =
    //     // {"(주)플러스냉동설비","광성냉동","천일냉동산업","협승냉열","라온오토텍","삼우정밀","대하냉기","제니스텍","정우냉동산업","주식회사
    //     // 피케이테크"};

    //     StringBuffer response = new StringBuffer();

    //     SimpleDateFormat format1 = new SimpleDateFormat("YYYYMMddHHmmss");
    //     Date time = new Date();

    //     JSONObject jsonObject1 = new JSONObject();
    //     JSONArray jsonArray1 = new JSONArray();
    //     JSONObject finalJsonObject = new JSONObject();

    //     for (int i = 0; i < kpiCertKeyList.length; i++) {
    //         jsonObject1 = new JSONObject();
    //         jsonArray1 = new JSONArray();
    //         finalJsonObject = new JSONObject();

    //         jsonObject1.put("kpiCertKey", kpiCertKeyList[i]);
    //         jsonObject1.put("ocrDttm", format1.format(time));
    //         jsonObject1.put("systmOprYn", "Y");
    //         jsonObject1.put("trsDttm", format1.format(time));
    //         jsonArray1.add(jsonObject1);

    //         finalJsonObject.put("KPILEVEL1", jsonArray1);

    //         System.out.println(finalJsonObject);

    //         String apiURL = "http://www.ssf-kpi.kr:8080/kpiLv1/kpiLv1Insert";
    //         URL url = new URL(apiURL);
    //         HttpURLConnection con = (HttpURLConnection) url.openConnection();
    //         con.setRequestMethod("POST");
    //         con.setRequestProperty("Content-type", "application/json");
    //         con.setRequestProperty("Accept-Charset", "UTF-8");
    //         con.setDoOutput(true);

    //         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    //         wr.writeBytes(finalJsonObject.toString());
    //         wr.flush();
    //         wr.close();
    //         int responseCode = con.getResponseCode();

    //         BufferedReader br;
    //         if (responseCode == 200) { // 정상 호출
    //             br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
    //         } else { // 에러 발생
    //             br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
    //         }
    //         String inputLine;
    //         response = new StringBuffer();
    //         while ((inputLine = br.readLine()) != null) {
    //             response.append(inputLine);
    //         }
    //         br.close();
    //         System.out.println(response.toString());
    //     }
    // }

    // @Scheduled(cron = "5 0 0 1 * *") // KPI L2 매달 1일 0시 0분 5초에 실행
    // public void kpi_level_2() throws Exception {
    //     String[] kpiCertKeyList = { "3e3e-aada-3728-0cc7", "d283-6aa2-06d5-57f4", "b896-2e86-9cd0-ba77",
    //             "6096-23ae-655d-41d6", "0cbf-f455-5c7d-ffb4", "2ca3-ed03-8c8d-f483", "f736-869c-5bd0-f0a0" };
    //     // String[] kpiCertKeyList2 =
    //     // {"580-87-00924","502-28-16002","616-13-36910","616-26-97858","606-81-66231","412-08-30135","165-81-02003"};
    //     String[] kpiCertKeyList3 = { "(주)플러스냉동설비", "광성냉동", "천일냉동산업", "협승냉열", "대하냉기", "정우냉동산업", "주식회사 피케이테크" };
    //     String[][] kpiFldCdList = { { "D", "C" }, { "D", "C" }, { "D", "C" }, { "D", "C" }, { "D", "C" }, { "D", "C" },
    //             { "D", "C" } }; // 세부지표분야코드
    //     String[][] kpiDtlCdList = { { "C", "E" }, { "C", "E" }, { "C", "E" }, { "C", "E" }, { "C", "E" }, { "C", "E" },
    //             { "C", "E" } }; // 세부성과지표코드
    //     String[][] kpiDtlNmList = { { "수주출하리드타임", "작업공수 절감" }, { "수주출하리드타임", "작업공수 절감" }, { "수주출하리드타임", "작업공수 절감" },
    //             { "수주출하리드타임", "작업공수 절감" }, { "수주출하리드타임", "작업공수 절감" }, { "수주출하리드타임", "작업공수 절감" },
    //             { "수주출하리드타임", "작업공수 절감" } };
    //     String[] cmpnNoList = { "2", "3", "4", "5", "6", "34", "35" };
    //     String[] dateList = { "3", "5", "5", "5", "5", "4", "2" };
    //     String[] timeList = { "5", "4", "5", "4", "4", "3", "2" };

    //     StringBuffer response = new StringBuffer();

    //     SimpleDateFormat format1 = new SimpleDateFormat("YYYYMMddHHmmss");
    //     Date time = new Date();

    //     JSONObject jsonObject1 = new JSONObject();
    //     JSONArray jsonArray1 = new JSONArray();
    //     JSONObject finalJsonObject = new JSONObject();
    //     HashMap<String, Object> body = new HashMap<String, Object>();
    //     List<HashMap<String, Object>> kpi2_date = new ArrayList<HashMap<String, Object>>();
    //     List<HashMap<String, Object>> kpi2_time = new ArrayList<HashMap<String, Object>>();

    //     for (int i = 0; i < kpiCertKeyList.length; i++) {
    //         for (int j = 0; j < kpiFldCdList[i].length; j++) {
    //             body.put("cmpn_no", cmpnNoList[i]);

    //             if (j == 0) {
    //                 kpi2_date = coolService.kpi2_date(body);

    //                 double achrt = 0;

    //                 if (kpi2_date != null) {
    //                     if (kpi2_date.get(0) != null) {
    //                         achrt = ((Double.parseDouble(dateList[i])
    //                                 - Double.parseDouble(kpi2_date.get(0).get("as_date").toString()))
    //                                 / Double.parseDouble(dateList[i])) * 100;
    //                     }
    //                 }

    //                 jsonObject1 = new JSONObject();
    //                 jsonArray1 = new JSONArray();
    //                 finalJsonObject = new JSONObject();

    //                 jsonObject1.put("kpiCertKey", kpiCertKeyList[i]);
    //                 jsonObject1.put("ocrDttm", format1.format(time));
    //                 jsonObject1.put("kpiFldCd", kpiFldCdList[i][j]);
    //                 jsonObject1.put("kpiDtlCd", kpiDtlCdList[i][j]);
    //                 jsonObject1.put("kpiDtlNm", kpiDtlNmList[i][j]);
    //                 jsonObject1.put("achrt", String.valueOf(achrt));
    //                 jsonObject1.put("trsDttm", format1.format(time));
    //                 jsonArray1.add(jsonObject1);

    //                 finalJsonObject.put("KPILEVEL2", jsonArray1);

    //                 // if(cmpnNoList[i] == "35"){
    //                 // System.out.println(finalJsonObject);
    //                 // }
    //                 System.out.println(kpiCertKeyList3[i] + " - " + finalJsonObject);

    //                 String apiURL = "http://www.ssf-kpi.kr:8080/kpiLv2/kpiLv2Insert";
    //                 URL url = new URL(apiURL);
    //                 HttpURLConnection con = (HttpURLConnection) url.openConnection();
    //                 con.setRequestMethod("POST");
    //                 con.setRequestProperty("Content-type", "application/json");
    //                 con.setRequestProperty("Accept-Charset", "UTF-8");
    //                 con.setDoOutput(true);

    //                 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    //                 wr.write(finalJsonObject.toString().getBytes("UTF-8"));
    //                 wr.flush();
    //                 wr.close();
    //                 int responseCode = con.getResponseCode();

    //                 BufferedReader br;
    //                 if (responseCode == 200) { // 정상 호출
    //                     br = new BufferedReader(new InputStreamReader(con.getInputStream(),
    //                             "UTF-8"));
    //                 } else { // 에러 발생
    //                     br = new BufferedReader(new InputStreamReader(con.getErrorStream(),
    //                             "UTF-8"));
    //                 }
    //                 String inputLine;
    //                 response = new StringBuffer();
    //                 while ((inputLine = br.readLine()) != null) {
    //                     response.append(inputLine);
    //                 }
    //                 br.close();
    //                 System.out.println(response.toString());
    //                 Thread.sleep(1500);
    //             }

    //             if (j == 1) {
    //                 kpi2_time = coolService.kpi2_time(body);

    //                 double achrt = 0;

    //                 if (kpi2_time.get(0) != null) {
    //                     if (kpi2_time.get(0) != null) {
    //                         achrt = ((Double.parseDouble(timeList[i])
    //                                 - Double.parseDouble(kpi2_time.get(0).get("as_time").toString()))
    //                                 / Double.parseDouble(timeList[i])) * 100;
    //                     }
    //                 }

    //                 jsonObject1 = new JSONObject();
    //                 jsonArray1 = new JSONArray();
    //                 finalJsonObject = new JSONObject();

    //                 jsonObject1.put("kpiCertKey", kpiCertKeyList[i]);
    //                 jsonObject1.put("ocrDttm", format1.format(time));
    //                 jsonObject1.put("kpiFldCd", kpiFldCdList[i][j]);
    //                 jsonObject1.put("kpiDtlCd", kpiDtlCdList[i][j]);
    //                 jsonObject1.put("kpiDtlNm", kpiDtlNmList[i][j]);
    //                 jsonObject1.put("achrt", String.valueOf(achrt));
    //                 jsonObject1.put("trsDttm", format1.format(time));
    //                 jsonArray1.add(jsonObject1);

    //                 finalJsonObject.put("KPILEVEL2", jsonArray1);

    //                 // if(cmpnNoList[i] == "35"){
    //                 // System.out.println(finalJsonObject);
    //                 // }
    //                 System.out.println(kpiCertKeyList3[i] + " - " + finalJsonObject);
    //                 String apiURL = "http://www.ssf-kpi.kr:8080/kpiLv2/kpiLv2Insert";
    //                 URL url = new URL(apiURL);
    //                 HttpURLConnection con = (HttpURLConnection) url.openConnection();
    //                 con.setRequestMethod("POST");
    //                 con.setRequestProperty("Content-type", "application/json");
    //                 con.setRequestProperty("Accept-Charset", "UTF-8");
    //                 con.setDoOutput(true);

    //                 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    //                 wr.write(finalJsonObject.toString().getBytes("UTF-8"));
    //                 wr.flush();
    //                 wr.close();
    //                 int responseCode = con.getResponseCode();

    //                 BufferedReader br;
    //                 if (responseCode == 200) { // 정상 호출
    //                     br = new BufferedReader(new InputStreamReader(con.getInputStream(),
    //                             "UTF-8"));
    //                 } else { // 에러 발생
    //                     br = new BufferedReader(new InputStreamReader(con.getErrorStream(),
    //                             "UTF-8"));
    //                 }
    //                 String inputLine;
    //                 response = new StringBuffer();
    //                 while ((inputLine = br.readLine()) != null) {
    //                     response.append(inputLine);
    //                 }
    //                 br.close();
    //                 System.out.println(response.toString());
    //                 Thread.sleep(1500);
    //             }

    //         }
    //     }
    // }

    // private static String[] Add(String[] originArray, String Val) {
    //     // 순서 1. 배열을 List로 변환
    //     List<String> newList = new ArrayList<>(Arrays.asList(originArray));

    //     // 순서 2. List의 Add() 메서드를 호출하여 새로운 값을 할당
    //     newList.add(Val);

    //     // 순서 3. List를 배열을 변환 후 반환
    //     return newList.toArray(new String[0]);
    // }

    // @Scheduled(cron = "50 59 23 * * *") // KPI L3 매일 23시 59분 59초에 실행
    // public void kpi_level_3() throws Exception {
    //     String[] kpiCertKeyList = { "3e3e-aada-3728-0cc7", "d283-6aa2-06d5-57f4", "b896-2e86-9cd0-ba77",
    //             "6096-23ae-655d-41d6", "0cbf-f455-5c7d-ffb4", "2ca3-ed03-8c8d-f483", "f736-869c-5bd0-f0a0" };
    //     // String[] kpiCertKeyList2 =
    //     // {"580-87-00924","502-28-16002","616-13-36910","616-26-97858","606-81-66231","412-08-30135","165-81-02003"};
    //     // String[] kpiCertKeyList3 =
    //     // {"(주)플러스냉동설비","광성냉동","천일냉동산업","협승냉열","대하냉기","정우냉동산업","주식회사 피케이테크"};

    //     String[][] kpiFldCdList = { { "D", "C" }, { "D", "C" }, { "D", "C" }, { "D", "C" }, { "D", "C" }, { "D", "C" },
    //             { "D", "C" } }; // 세부지표분야코드
    //     String[][] kpiDtlCdList = { { "C", "E" }, { "C", "E" }, { "C", "E" }, { "C", "E" }, { "C", "E" }, { "C", "E" },
    //             { "C", "E" } }; // 세부성과지표코드
    //     String[][] kpiDtlNmList = { { "수주출하리드타임", "작업공수 절감" }, { "수주출하리드타임", "작업공수 절감" }, { "수주출하리드타임", "작업공수 절감" },
    //             { "수주출하리드타임", "작업공수 절감" }, { "수주출하리드타임", "작업공수 절감" }, { "수주출하리드타임", "작업공수 절감" },
    //             { "수주출하리드타임", "작업공수 절감" } };
    //     String[][] untList = { { "일", "시간" }, { "일", "시간" }, { "일", "시간" }, { "일", "시간" }, { "일", "시간" }, { "일", "시간" },
    //             { "일", "시간" } }; // 세부성과지표코드
    //     String[] cmpnNoList = { "2", "3", "4", "5", "6", "34", "35" };

    //     StringBuffer response = new StringBuffer();

    //     SimpleDateFormat format1 = new SimpleDateFormat("YYYYMMddHHmmss");
    //     Date time = new Date();

    //     JSONObject jsonObject1 = new JSONObject();
    //     JSONArray jsonArray1 = new JSONArray();
    //     JSONObject finalJsonObject = new JSONObject();
    //     HashMap<String, Object> body = new HashMap<String, Object>();
    //     List<HashMap<String, Object>> kpi3_date = new ArrayList<HashMap<String, Object>>();
    //     List<HashMap<String, Object>> kpi3_time = new ArrayList<HashMap<String, Object>>();

    //     for (int i = 0; i < kpiCertKeyList.length; i++) {
    //         for (int j = 0; j < kpiFldCdList[i].length; j++) {
    //             body.put("cmpn_no", cmpnNoList[i]);

    //             if (j == 0) {
    //                 kpi3_date = coolService.kpi3_date(body);

    //                 if (kpi3_date.size() == 0) {
    //                     jsonObject1 = new JSONObject();
    //                     jsonArray1 = new JSONArray();
    //                     finalJsonObject = new JSONObject();

    //                     jsonObject1.put("kpiCertKey", kpiCertKeyList[i]);
    //                     jsonObject1.put("ocrDttm", format1.format(time));
    //                     jsonObject1.put("kpiFldCd", kpiFldCdList[i][j]);
    //                     jsonObject1.put("kpiDtlCd", kpiDtlCdList[i][j]);
    //                     jsonObject1.put("kpiDtlNm", kpiDtlNmList[i][j]);
    //                     jsonObject1.put("msmtVl", "0");
    //                     jsonObject1.put("unt", untList[i][j]);
    //                     jsonObject1.put("trsDttm", format1.format(time));
    //                     jsonArray1.add(jsonObject1);

    //                     finalJsonObject.put("KPILEVEL3", jsonArray1);

    //                     System.out.println(finalJsonObject);

    //                     String apiURL = "http://www.ssf-kpi.kr:8080/kpiLv3/kpiLv3Insert";
    //                     URL url = new URL(apiURL);
    //                     HttpURLConnection con = (HttpURLConnection) url.openConnection();
    //                     con.setRequestMethod("POST");
    //                     con.setRequestProperty("Content-type", "application/json;UTF-8");
    //                     // con.setRequestProperty("Accept-Charset", "UTF-8");
    //                     con.setDoOutput(true);

    //                     DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    //                     wr.write(finalJsonObject.toString().getBytes("UTF-8"));
    //                     wr.flush();
    //                     wr.close();
    //                     int responseCode = con.getResponseCode();

    //                     BufferedReader br;
    //                     if (responseCode == 200) { // 정상 호출
    //                         br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
    //                     } else { // 에러 발생
    //                         br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
    //                     }
    //                     String inputLine;
    //                     response = new StringBuffer();
    //                     while ((inputLine = br.readLine()) != null) {
    //                         response.append(inputLine);
    //                     }
    //                     br.close();
    //                     System.out.println(response.toString());
    //                 } else {
    //                     for (int k = 0; k < kpi3_date.size(); k++) {
    //                         jsonObject1 = new JSONObject();
    //                         jsonArray1 = new JSONArray();
    //                         finalJsonObject = new JSONObject();

    //                         jsonObject1.put("kpiCertKey", kpiCertKeyList[i]);
    //                         jsonObject1.put("ocrDttm", format1.format(time));
    //                         jsonObject1.put("kpiFldCd", kpiFldCdList[i][j]);
    //                         jsonObject1.put("kpiDtlCd", kpiDtlCdList[i][j]);
    //                         jsonObject1.put("kpiDtlNm", kpiDtlNmList[i][j]);
    //                         jsonObject1.put("msmtVl", kpi3_date.get(k).get("as_date").toString());
    //                         jsonObject1.put("unt", untList[i][j]);
    //                         jsonObject1.put("trsDttm", format1.format(time));
    //                         jsonArray1.add(jsonObject1);

    //                         finalJsonObject.put("KPILEVEL3", jsonArray1);

    //                         System.out.println(finalJsonObject);

    //                         String apiURL = "http://www.ssf-kpi.kr:8080/kpiLv3/kpiLv3Insert";
    //                         URL url = new URL(apiURL);
    //                         HttpURLConnection con = (HttpURLConnection) url.openConnection();
    //                         con.setRequestMethod("POST");
    //                         con.setRequestProperty("Content-type", "application/json;UTF-8");
    //                         // con.setRequestProperty("Accept-Charset", "UTF-8");
    //                         con.setDoOutput(true);

    //                         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    //                         wr.write(finalJsonObject.toString().getBytes("UTF-8"));
    //                         wr.flush();
    //                         wr.close();
    //                         int responseCode = con.getResponseCode();

    //                         BufferedReader br;
    //                         if (responseCode == 200) { // 정상 호출
    //                             br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
    //                         } else { // 에러 발생
    //                             br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
    //                         }
    //                         String inputLine;
    //                         response = new StringBuffer();
    //                         while ((inputLine = br.readLine()) != null) {
    //                             response.append(inputLine);
    //                         }
    //                         br.close();
    //                         System.out.println(response.toString());
    //                     }
    //                 }
    //             }

    //             if (j == 1) {
    //                 kpi3_time = coolService.kpi3_time(body);

    //                 if (kpi3_time.size() == 0) {
    //                     jsonObject1 = new JSONObject();
    //                     jsonArray1 = new JSONArray();
    //                     finalJsonObject = new JSONObject();

    //                     jsonObject1.put("kpiCertKey", kpiCertKeyList[i]);
    //                     jsonObject1.put("ocrDttm", format1.format(time));
    //                     jsonObject1.put("kpiFldCd", kpiFldCdList[i][j]);
    //                     jsonObject1.put("kpiDtlCd", kpiDtlCdList[i][j]);
    //                     jsonObject1.put("kpiDtlNm", kpiDtlNmList[i][j]);
    //                     jsonObject1.put("msmtVl", "0");
    //                     jsonObject1.put("unt", untList[i][j]);
    //                     jsonObject1.put("trsDttm", format1.format(time));
    //                     jsonArray1.add(jsonObject1);

    //                     finalJsonObject.put("KPILEVEL3", jsonArray1);

    //                     System.out.println(finalJsonObject);

    //                     String apiURL = "http://www.ssf-kpi.kr:8080/kpiLv3/kpiLv3Insert";
    //                     URL url = new URL(apiURL);
    //                     HttpURLConnection con = (HttpURLConnection) url.openConnection();
    //                     con.setRequestMethod("POST");
    //                     con.setRequestProperty("Content-type", "application/json;UTF-8");
    //                     // con.setRequestProperty("Accept-Charset", "UTF-8");
    //                     con.setDoOutput(true);

    //                     DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    //                     wr.write(finalJsonObject.toString().getBytes("UTF-8"));
    //                     wr.flush();
    //                     wr.close();
    //                     int responseCode = con.getResponseCode();

    //                     BufferedReader br;
    //                     if (responseCode == 200) { // 정상 호출
    //                         br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
    //                     } else { // 에러 발생
    //                         br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
    //                     }
    //                     String inputLine;
    //                     response = new StringBuffer();
    //                     while ((inputLine = br.readLine()) != null) {
    //                         response.append(inputLine);
    //                     }
    //                     br.close();
    //                     System.out.println(response.toString());
    //                 } else {
    //                     for (int k = 0; k < kpi3_time.size(); k++) {
    //                         jsonObject1 = new JSONObject();
    //                         jsonArray1 = new JSONArray();
    //                         finalJsonObject = new JSONObject();

    //                         jsonObject1.put("kpiCertKey", kpiCertKeyList[i]);
    //                         jsonObject1.put("ocrDttm", format1.format(time));
    //                         jsonObject1.put("kpiFldCd", kpiFldCdList[i][j]);
    //                         jsonObject1.put("kpiDtlCd", kpiDtlCdList[i][j]);
    //                         jsonObject1.put("kpiDtlNm", kpiDtlNmList[i][j]);
    //                         jsonObject1.put("msmtVl", kpi3_time.get(k).get("as_time").toString());
    //                         jsonObject1.put("unt", untList[i][j]);
    //                         jsonObject1.put("trsDttm", format1.format(time));
    //                         jsonArray1.add(jsonObject1);

    //                         finalJsonObject.put("KPILEVEL3", jsonArray1);

    //                         System.out.println(finalJsonObject);

    //                         String apiURL = "http://www.ssf-kpi.kr:8080/kpiLv3/kpiLv3Insert";
    //                         URL url = new URL(apiURL);
    //                         HttpURLConnection con = (HttpURLConnection) url.openConnection();
    //                         con.setRequestMethod("POST");
    //                         con.setRequestProperty("Content-type", "application/json;UTF-8");
    //                         // con.setRequestProperty("Accept-Charset", "UTF-8");
    //                         con.setDoOutput(true);

    //                         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    //                         wr.write(finalJsonObject.toString().getBytes("UTF-8"));
    //                         wr.flush();
    //                         wr.close();
    //                         int responseCode = con.getResponseCode();

    //                         BufferedReader br;
    //                         if (responseCode == 200) { // 정상 호출
    //                             br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
    //                         } else { // 에러 발생
    //                             br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
    //                         }
    //                         String inputLine;
    //                         response = new StringBuffer();
    //                         while ((inputLine = br.readLine()) != null) {
    //                             response.append(inputLine);
    //                         }
    //                         br.close();
    //                         System.out.println(response.toString());
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }

}
