package com.how2java.pojo;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("p")
public class Product {
	private int id;
    private String name = "product 1";
    @Resource(name="c") //@Autowired //ע�������ע�����
    private Category category;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Category getCategory() {
        return category;
    }
    //����ǰ��� ������ǰ����@Autowired ���ַ�ʽ�⣬Ҳ������setCategory����ǰ����@Autowired���������ﵽ��ͬ��Ч��
    //@Autowired
    public void setCategory(Category category) {
        this.category = category;
    }
}
