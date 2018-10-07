package com.hhwit.liaisonshome;

/**
 * Created by Hongwen Huang on 2018/10/6
 * Email: hhwit@126.com
 */

public interface ListViewCallback<T> {
    void OnItemClickListener(int position, T bean, int flag);
    void OnItemLongClickListener(int position, T bean, int flag);
}
