package com.huchang.haxi.shoppingclient.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huchang.haxi.shoppingclient.R;
import com.huchang.haxi.shoppingclient.myclass.ModeHomeMenu;
import com.huchang.haxi.shoppingclient.utils.ScreenUtil;

import org.w3c.dom.Text;

import java.util.List;


public class HomeMenuEnterAdapter extends RecyclerView.Adapter<HomeMenuEnterAdapter.HomeMenuViewHolder> {

    private Context mcontext;
    private List<ModeHomeMenu> mdatas;
    private int mindex;
    private int mpageSize;

    public HomeMenuEnterAdapter(Context context, List<ModeHomeMenu> datas, int index, int pageSize) {
        mcontext = context;
        mdatas = datas;
        mindex = index;
        mpageSize = pageSize;
    }

    @NonNull
    @Override
    public HomeMenuEnterAdapter.HomeMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        HomeMenuViewHolder holder = new HomeMenuViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_home_enterace,viewGroup,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuEnterAdapter.HomeMenuViewHolder homeMenuViewHolder, int i) {
        final int pos = i+mindex*mpageSize;
        homeMenuViewHolder.tv.setText(mdatas.get(pos).getName());
        homeMenuViewHolder.imageView.setImageResource(mdatas.get(pos).getImage());
    }

    @Override
    public int getItemCount() {
        return mdatas.size()>(mindex+1)*mpageSize?mpageSize:(mdatas.size()-mindex*mpageSize);
    }

    @Override
    public long getItemId(int position) {
        return position+mindex*mpageSize;
    }

    class HomeMenuViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tv;

        public HomeMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.entrance_image);
            tv = (TextView)itemView.findViewById(R.id.entrance_name);
            //控制itemView布局的大小
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,ScreenUtil.getScreenWidth()/4);
            itemView.setLayoutParams(layoutParams);

        }
    }

}
