package com.qin.dao;

import com.qin.pojo.Carousel;
import java.util.List;

public interface CarouselMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table laf_carousel
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table laf_carousel
     *
     * @mbg.generated
     */
    int insert(Carousel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table laf_carousel
     *
     * @mbg.generated
     */
    Carousel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table laf_carousel
     *
     * @mbg.generated
     */
    List<Carousel> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table laf_carousel
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Carousel record);
}