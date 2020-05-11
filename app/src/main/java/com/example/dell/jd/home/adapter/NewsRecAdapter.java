package com.example.dell.jd.home.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.dell.jd.R;
import com.example.dell.jd.home.bean.NewsBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class NewsRecAdapter extends BaseMultiItemQuickAdapter<NewsBean.ResultData,BaseViewHolder> {
    public NewsRecAdapter(List<NewsBean.ResultData> data) {
        super(data);
        addItemType(NewsBean.ResultData.TYPE_LIST, R.layout.flash_item);
        addItemType(NewsBean.ResultData.TYPE_TE_XIE, R.layout.aeticle_item);
        addItemType(NewsBean.ResultData.TYPE_VIDEO, R.layout.video_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean.ResultData resultData) {

        int itemViewType = baseViewHolder.getItemViewType();
        if(itemViewType== NewsBean.ResultData.TYPE_LIST ){
            list(baseViewHolder,resultData);
        }else if (itemViewType==NewsBean.ResultData.TYPE_TE_XIE){
            teXie(baseViewHolder,resultData);
        }else if (itemViewType==NewsBean.ResultData.TYPE_VIDEO){
            video(baseViewHolder,resultData);
        }else if (itemViewType==NewsBean.ResultData.TYPE_FLASH){

        }
    }

    private void video(BaseViewHolder baseViewHolder, NewsBean.ResultData resultData) {
        NewsBean.DataBean.ArticleListBean articleListBean = (NewsBean.DataBean.ArticleListBean) resultData.data;

        JCVideoPlayerStandard video = baseViewHolder.findView(R.id.video_news_item);

        video.setUp(articleListBean.getVideo_url(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,articleListBean.getLead());
//        Glide.with(getContext()).load(articleListBean.getVideo_url()).into((ImageView) baseViewHolder.getView(R.id.video_news_item));
        baseViewHolder.setText(R.id.tv1_news_item,articleListBean.getTheme());
        baseViewHolder.setText(R.id.tv2_news_item,articleListBean.getColumn_name());
    }

    private void teXie(BaseViewHolder baseViewHolder, NewsBean.ResultData resultData) {
        NewsBean.DataBean.ArticleListBean articleListBean = (NewsBean.DataBean.ArticleListBean) resultData.data;
        Glide.with(getContext()).load(articleListBean.getImage_url()).into((ImageView) baseViewHolder.getView(R.id.img_news_item));
        baseViewHolder.setText(R.id.tv1_news_item,articleListBean.getTheme());
        baseViewHolder.setText(R.id.tv2_news_item,articleListBean.getColumn_name());
    }

    private void list(BaseViewHolder baseViewHolder, NewsBean.ResultData resultData) {
        NewsBean.DataBean.ArticleListBean articleListBean = (NewsBean.DataBean.ArticleListBean) resultData.data;
        Glide.with(getContext()).load(articleListBean.getImage_url()).override(150,100).into((ImageView) baseViewHolder.getView(R.id.img_news_item));
        baseViewHolder.setText(R.id.tv1_news_item,articleListBean.getTheme());
        baseViewHolder.setText(R.id.tv2_news_item,articleListBean.getColumn_name());
    }
}
