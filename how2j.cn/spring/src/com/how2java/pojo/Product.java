package com.how2java.pojo;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("p")
public class Product {
	private int id;
    private String name = "product 1";
    @Resource(name="c") //@Autowired //注解来完成注入对象
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
    //除了前面的 在属性前加上@Autowired 这种方式外，也可以在setCategory方法前加上@Autowired，这样来达到相同的效果
    //@Autowired
    public void setCategory(Category category) {
        this.category = category;
    }
}
