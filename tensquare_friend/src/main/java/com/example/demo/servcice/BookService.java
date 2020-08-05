/**
 * ClassName:BookService
 * Author:Administrator
 * Date:2020/5/16 001612:18
 * Description:TODO
 */
package com.example.demo.servcice;

import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: yuliang
 * @Date: 2020/5/16 0016 12:18
 */
@Service
@Component
public class BookService {

	@Autowired
	private BookMapper bookmapper;
	public Book  selectbook() {
		Book book = bookmapper.selectByPrimaryKey(1);
		return book;
	}

}
