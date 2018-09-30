package com.hhwit.liaisonshome;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Hongwen Huang on 2018/9/26
 * Email: hhwit@126.com
 */

public class DiscoverViewHolder extends RecyclerView.ViewHolder {
    public TextView description;
    public TextView identity;
    public TextView footerText;

    public DiscoverViewHolder(View itemView) {
        super(itemView);

        description = itemView.findViewById(R.id.item_discover_description);
        identity = itemView.findViewById(R.id.item_discover_identity);
        footerText = itemView.findViewById(R.id.footer_text);

    }
}
