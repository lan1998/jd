package com.example.dell.jd.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.dell.jd.R;
import com.example.dell.jd.home.bean.NewsBean;

import java.util.List;

public class NewsRecAdapter extends BaseMultiItemQuickAdapter<NewsBean.ResultData,BaseViewHolder> {
    public NewsRecAdapter(List<NewsBean.ResultData> data) {
        super(data);
        addItemType(NewsBean.ResultData.TYPE_LIST, R.layout.flash_item);
        addItemType(NewsBean.ResultData.TYPE_TE_XIE, R.layout.aeticle_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean.ResultData resultData) {

        /*switch (baseViewHolder.getItemViewType()){
            case NewsBean.ResultData.TYPE_LIST:
                list(baseViewHolder,resultData);
                break;
            case NewsBean.ResultData.TYPE_TE_XIE:
                list(baseViewHolder,resultData);
                break;
        }*/
        int itemViewType = baseViewHolder.getItemViewType();
        if(itemViewType== NewsBean.ResultData.TYPE_LIST ||itemViewType==NewsBean.ResultData.TYPE_TE_XIE){
            list(baseViewHolder,resultData);
        }
    }

    private void list(BaseViewHolder baseViewHolder, NewsBean.ResultData resultData) {
        NewsBean.DataBean.ArticleListBean articleListBean = (NewsBean.DataBean.ArticleListBean) resultData.data;
        Glide.with(getContext()).load(articleListBean.getImage_url()).into((ImageView) baseViewHolder.getView(R.id.img_news_item));
        baseViewHolder.setText(R.id.tv1_news_item,articleListBean.getTheme());
        baseViewHolder.setText(R.id.tv2_news_item,articleListBean.getColumn_name());
    }
}
