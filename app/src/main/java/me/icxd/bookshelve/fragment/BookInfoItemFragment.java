package me.icxd.bookshelve.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.icxd.bookshelve.adapter.BookInfoAdapter;
import me.icxd.bookshelve.R;
import me.icxd.bookshelve.model.bean.Book;

public class BookInfoItemFragment extends Fragment {
    private long mItemId = 1;
    private static final String ARG_ITEM_ID = "item_id";
    private List<Map<String, Object>> data;

    public static BookInfoItemFragment newInstance(long itemId) {
        BookInfoItemFragment fragment = new BookInfoItemFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ITEM_ID, itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemId = getArguments().getLong(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView lv = (ListView) inflater.inflate(R.layout.fragment_book_info_item_list, container, false);

        // 图书数据
        Book book = DataSupport.find(Book.class, mItemId);

        // 数据
        data = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("tag", "作者");
        map.put("content", book.getAuthor());
        data.add(map);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("tag", "出版社");
        map1.put("content", book.getPublisher());
        data.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("tag", "评分");
        map2.put("content", book.getAverage());
        data.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("tag", "出版年");
        map3.put("content", book.getPubdate());
        data.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("tag", "定价");
        map4.put("content", book.getPrice());
        data.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("tag", "ISBN");
        map5.put("content", book.getIsbn13());
        data.add(map5);

        // 列表适配器
        BookInfoAdapter lvBaseAdapter = new BookInfoAdapter(getActivity(), data);

        // 列表
        lv.setAdapter(lvBaseAdapter);

        return lv;
    }
}
