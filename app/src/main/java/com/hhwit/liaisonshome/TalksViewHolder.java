package com.hhwit.liaisonshome;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Hongwen Huang on 2018/10/10
 * Email: hhwit@126.com
 */

public class TalksViewHolder extends RecyclerView.ViewHolder {
    public TextView description;
    public MultiImageView type;
    public TextView identity;
    public TextView content;
    public TextView date;

    TalksViewHolder(View itemView) {
        super(itemView);

        description = itemView.findViewById(R.id.item_talks_description);
        identity = itemView.findViewById(R.id.item_talks_identity);
        type = itemView.findViewById(R.id.item_talks_type);
        content = itemView.findViewById(R.id.item_talks_content);
        date = itemView.findViewById(R.id.item_talks_date);

    }
}
