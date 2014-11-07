package com.guo.util;

import com.guo.common.PageListResult;
import com.guo.common.PaginatedArrayList;
import com.ibatis.common.util.PaginatedList;
//import com.jd.common.util.PaginatedList;
//import com.jd.common.util.base.PaginatedArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: zhangrui1
 * Date: 13-12-6
 * Time: 下午5:59
 * Description: 系统工具
 */
public class SystemTools {
    /***
     * 封装saf分页数据到PaginatedList
     * @param list saf分页数据
     * @return PaginatedList
     */
    public static PaginatedArrayList convertPaginatedList(PageListResult<?> list){
        PaginatedArrayList paginatedList = null;
        if(list != null && list.getPagenation() != null){
            paginatedList = new PaginatedArrayList(list.getPagenation().getPageNo(),list.getPagenation().getPageSize(),list.getPagenation().getPageCount());
            paginatedList.setTotalPage(list.getPagenation().getPageCount());
            paginatedList.setPreviousPage(list.getPagenation().getPageNo() -1);
            paginatedList.setNextPage(list.getPagenation().getPageNo() +1);
            paginatedList.addAll(list.getValues());
        }
        return paginatedList;
    }
    
    /**
     * 拼树：不带checkbox
     * @param roleList
     * @return
     */
//    public static String list2TreeNoCheck(List<MenuFunction> roleList) {
//        if(roleList!=null && roleList.size()>0) {
//            List<TreeNode> treeList = new ArrayList<TreeNode>();
//            for(MenuFunction mf :roleList) {
//                System.out.println(mf.getAuthNam()+"--"+mf.getAuthTyp());
//                if(mf.getAuthTyp() != null && mf.getAuthTyp().equals("1")){
//                    continue;
//                }
//                TreeNode tn = new TreeNode();
//                tn.setId(mf.getAuthId());
//                String pId = "0";
//                if(mf.getParentAuthId()!=null && !mf.getParentAuthId().equals("")){
//                    pId =mf.getParentAuthId();
//                }
//                tn.setpId(pId);
//                tn.setName(mf.getAuthNam());
//                tn.setOpen(true); //设置所有打开或所有闭关
//                tn.setChecked(false);
//                tn.setDoCheck(false);
//                tn.setHalfCheck(false);
//                tn.setParent(false);
//                tn.setChkDisabled(false);
//                tn.setNocheck(false);
//                treeList.add(tn);
//            }
//            JSONArray jsonArray = JSONArray.fromObject(treeList);
//            return jsonArray.toString();
//        }
//        return "";
//    }

    /**
     * 拼树：带checkbox
     * @param allList 所有可用的权限
     * @param roleList  当前角色的权限
     * @return
     */
//    public static String list2TreeWithCheck(List<MenuFunction> allList,List<MenuFunction> roleList) {
//        if(allList!=null && allList.size()>0) {
//            List<TreeNode> treeList = new ArrayList<TreeNode>();
//            for(MenuFunction auth :allList) {
//                TreeNode tn = new TreeNode();
//                tn.setId(auth.getAuthId());
//                String pId = "0";
//                if(auth.getParentAuthId()!=null && !auth.getParentAuthId().equals("")){
//                    pId = auth.getParentAuthId();
//                }
//                tn.setpId(pId);
//                tn.setName(auth.getAuthNam());
//                tn.setOpen(true); //设置所有打开或所有闭关
//                tn.setChecked(false);
//                //判断是否选中
//                for(MenuFunction mf :roleList) {
//                    if(mf.getAuthId().equals(auth.getAuthId())){
//                        tn.setChecked(true);
//                        break;
//                    }
//
//                }
//                tn.setDoCheck(false);
//                tn.setHalfCheck(false);
//                tn.setParent(false);
//                tn.setChkDisabled(false);
//                tn.setNocheck(false);
//                treeList.add(tn);
//            }
//            JSONArray jsonArray = JSONArray.fromObject(treeList);
//            return jsonArray.toString();
//        }
//        return "";
//    }
}
