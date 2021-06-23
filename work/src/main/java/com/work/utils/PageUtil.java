package com.work.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jian
 *
 */
@Data
public class PageUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L; //序列化ID 用于序列化和反序列化配对
    private List<T> data = new ArrayList<>();
    private long totalElements;
    private long pageNumber;
    private long pageSize;
    private boolean first;
    private boolean last;
    private boolean isEmpty;
    private int totalPages;
    private int numberOfElement;

    public PageUtil(){ //空构造

    }
    public PageUtil(IPage<T> page){
        this.data = page.getRecords();//获取内容
        this.totalElements = page.getTotal();//获取所有元素的数量
        this.pageNumber = page.getCurrent();//获取当前页面数量
        this.pageSize = page.getSize();//获取当前页面元素数量
        this.numberOfElement = page.getRecords().size();//获取元素数量
        this.totalPages = (int)Math.ceil((double)totalElements/(double)pageSize);
}
    //判断是否有前一页
    public boolean hasPrevious(){
        return  getPageNumber()>0;
    }
    //判断是否有后一页
    public boolean hasNext(){
        return  getPageNumber()+1<getTotalPages();
    }
    //判断是否为第一页
    public boolean isFirst(){
        return  !hasPrevious();//没有前一页就是最后一页
    }
    //判断是否为最后一页
    public boolean isLast(){
        return !hasNext();
    }
    //判断是否有内容
    public boolean hasContent(){
        return  getNumberOfElement()>0;
    }
    //计算总页数 及总元素除以页面大小
    public int getTotalPage(){
        return  getPageSize() == 0?1: (int)Math.ceil((double)totalElements/(double)getPageSize());
    }
    //判断是否为空
    public  boolean isEmpty(){
        return !hasContent();
    }
}
