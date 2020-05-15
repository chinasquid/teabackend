package com.teabackend.teabackend.business.service;

import com.teabackend.teabackend.business.bean.GoodsItemDo;
import com.teabackend.teabackend.business.bean.GoodsListItemDO;
import com.teabackend.teabackend.business.bean.GoodsSearchOption;
import com.teabackend.teabackend.business.bean.ProductItemVO;
import com.teabackend.teabackend.business.dao.BusinessProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-12 15:32
 */
@Service
public class BusinessProductService {
    @Autowired
    private BusinessProductDao productDao;

    /**
     * 暂存商品图片地址
     *
     * @param fileRealPath 文件真实路径
     * @param fileVirtualPath 文件虚拟访问路径
     * @param user_id  用户id
     * @param img_id   图片id
     */
    public void saveGoodsTempPath(String fileRealPath,String fileVirtualPath, String user_id, String img_id) {
        productDao.insertGoodTempPath(fileRealPath,fileVirtualPath, user_id, img_id);
    }

    /**
     * 检查是否已经上传过图片，防止重复上传
     * @param user_id  用户id
     * @param img_id   图片id
     */

    public void checkTempImg(String user_id, String img_id) {
        productDao.deleteTempImg(user_id, img_id);
        System.out.println("删除暂存图片");
    }

    /**
     * 保存商品信息
     * @param productItemVO 商品具体信息
     */
    public void saveGoodsTempDetails(ProductItemVO productItemVO) {
        System.out.println("开始存储商品");
        try {
            productDao.updateGoodsDetails(productItemVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取商品全部信息
     * @param user_id 用户id
     * @return 商品信息列表
     */
    public ArrayList<GoodsItemDo> getGoodsList(String user_id) {
         return productDao.selectGoodsList(user_id);

    }

    /**
     * 含有此产品，则返回true
     * @param user_id 用户id
     * @param img_id 图片id
     * @return 含有此产品，则返回true
     */
    public boolean haveProduct(String user_id, String img_id) {
        Integer num = productDao.selectGoodsNum(user_id,img_id);
        return num > 0;
    }

    public ArrayList<GoodsListItemDO> getAllGoodsList(GoodsSearchOption searchOption)  {
        ArrayList<GoodsListItemDO> goodsList = new ArrayList<>();
        try {
            goodsList = productDao.getAllGoodsList(searchOption);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return goodsList;
    }
}
