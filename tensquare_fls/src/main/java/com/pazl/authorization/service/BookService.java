/**
 * ClassName:BookService
 * Author:Administrator
 * Date:2020/5/16 001612:18
 * Description:TODO
 */
package com.pazl.authorization.service;


import com.pazl.authorization.entity.Book;
import com.pazl.authorization.mapper.BookMapper;
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
