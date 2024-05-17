package com.cool.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoolAdminMapper {
    public List<HashMap<String, Object>> auth(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> adminOL(HashMap<String, Object> body) throws Exception;
    public void adminJoin(HashMap<String, Object> body) throws Exception;
    public void adminDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> adminLoginCheck(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> repairCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> repairList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> repairNo(HashMap<String, Object> body) throws Exception;
    public void repairPost(HashMap<String, Object> body) throws Exception;
    public void repairPut(HashMap<String, Object> body) throws Exception;
    public void repairDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> repairOL(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> repairCmpn(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> repairDownload(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> repairName(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> repairGlasses(HashMap<String, Object> body) throws Exception;
    public void repairGlassesPut(HashMap<String, Object> body) throws Exception;

    

    public List<HashMap<String, Object>> itemInfoCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemInfoList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemInfoNo(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemInfoItemCateNo(HashMap<String, Object> body) throws Exception;
    public void itemInfoPost(HashMap<String, Object> body) throws Exception;
    public void itemInfoPut(HashMap<String, Object> body) throws Exception;
    public void itemInfoDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemInfoOL(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> itemBomCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemBomList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemBomNo(HashMap<String, Object> body) throws Exception;
    public void itemBomPost(HashMap<String, Object> body) throws Exception;
    public void itemBomPut(HashMap<String, Object> body) throws Exception;
    public void itemBomDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemBomOL(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> itemCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemNo(HashMap<String, Object> body) throws Exception;
    public void itemPost(HashMap<String, Object> body) throws Exception;
    public void itemPut(HashMap<String, Object> body) throws Exception;
    public void itemDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemOL(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> itemDownload(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> splCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> splList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> splNo(HashMap<String, Object> body) throws Exception;
    public int splPost(HashMap<String, Object> body) throws Exception;
    public void splPut(HashMap<String, Object> body) throws Exception;
    public void splDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> splOL(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> splRepairCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> splRepairList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> splRepairNo(HashMap<String, Object> body) throws Exception;
    public void splRepairPost(HashMap<String, Object> body) throws Exception;
    public void splRepairPut(HashMap<String, Object> body) throws Exception;
    public void splRepairDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> splRepairOL(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> splRepairSplList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> splRepairDownload(HashMap<String, Object> body) throws Exception;
    
    public List<HashMap<String, Object>> prchCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> prchList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> prchNo(HashMap<String, Object> body) throws Exception;
    public void prchPost(HashMap<String, Object> body) throws Exception;
    public void prchPut(HashMap<String, Object> body) throws Exception;
    public void prchDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> prchOL(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> prcItmCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> prcItmList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> prcItmNo(HashMap<String, Object> body) throws Exception;
    public void prcItmPost(HashMap<String, Object> body) throws Exception;
    public void prcItmPut(HashMap<String, Object> body) throws Exception;
    public void prcItmDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> prcItmOL(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> prcItmPrch(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> cusCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> cusList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> cusAsCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> cusAsList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> cusNo(HashMap<String, Object> body) throws Exception;
    public void cusPost(HashMap<String, Object> body) throws Exception;
    public void cusPut(HashMap<String, Object> body) throws Exception;
    public void cusDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> cusOL(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> cusDownload(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> cusName(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> asCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> asList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> asNo(HashMap<String, Object> body) throws Exception;
    public int asPost(HashMap<String, Object> body) throws Exception;
    public void asPut(HashMap<String, Object> body) throws Exception;
    public void asDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> asDownload(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> asCusCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> asCusList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> asRepairList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> asCmpnList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> asInsitemList(HashMap<String, Object> body) throws Exception;
    
    public List<HashMap<String, Object>> glassesCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> glassesList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> glassesNo(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> glassesOL(HashMap<String, Object> body) throws Exception;
    public void glassesPut(HashMap<String, Object> body) throws Exception;
    public void glassesTokenPut(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> userCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> userList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> userNo(HashMap<String, Object> body) throws Exception;
    public void userPost(HashMap<String, Object> body) throws Exception;
    public void userPut(HashMap<String, Object> body) throws Exception;
    public void userDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> userOL(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> userAllList(HashMap<String, Object> body) throws Exception;
    public void userAcesPut(HashMap<String, Object> body) throws Exception;

    public void asDateS(HashMap<String, Object> body) throws Exception;
    public void asDateE(HashMap<String, Object> body) throws Exception;

    public void tokenAdminPost(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> insItemCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> insItemList(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> insItemNo(HashMap<String, Object> body) throws Exception;
    public void insItemPost(HashMap<String, Object> body) throws Exception;
    public void insItemPut(HashMap<String, Object> body) throws Exception;
    public void insItemDelete(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> insItemOL(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> insItemDownload(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> cmpnName(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> mtrMngPrch(HashMap<String, Object> body) throws Exception;
    public void mtrMng_save(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> mtrMngCnt(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> mtrMng(HashMap<String, Object> body) throws Exception;
    public List<HashMap<String, Object>> mtrMng_findById(HashMap<String, Object> body) throws Exception;
    public void mtrMng_update(HashMap<String, Object> body) throws Exception;
    public void mtrMng_delete(HashMap<String, Object> body) throws Exception;
}
