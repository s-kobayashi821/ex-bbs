package com.example.repository;

import com.example.domain.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * hotelsテーブルを操作するリポジトリ.
 *
 */
@Repository
public class HotelRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<Hotel> HOTEL_ROW_MAPPER = (rs, rowNum) ->{
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setAreaName(rs.getString("area_name"));
        hotel.setHotelName(rs.getString("hotel_name"));
        hotel.setAddress(rs.getString("address"));
        hotel.setNearestStation(rs.getString("nearest_station"));
        hotel.setPrice(rs.getInt("price"));
        hotel.setParking(rs.getString("parking"));
        return hotel;
    };


    /**
     * cost以下で泊まれるホテル情報を検索する.
     *
     * @return 条件に合うホテル一覧情報
     */
    public List<Hotel> findByLeqPrice(final int price){
        String sql = "SELECT id, area_name, hotel_name, address, nearest_station, price, parking " +
                "FROM hotels WHERE price <= :price ORDER BY price DESC;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("price", price);
        List<Hotel> hotelList = template.query(sql, param, HOTEL_ROW_MAPPER);
        return  hotelList;
    }

    /**
     * すべてのホテル情報を検索する.
     *
     * @return ホテル一覧情報
     */
    public List<Hotel> findAll(){
        String sql = "SELECT id, area_name, hotel_name, address, nearest_station, price, parking " +
                "FROM hotels ORDER BY price DESC;";
        List<Hotel> hotelList = template.query(sql, HOTEL_ROW_MAPPER);
        return  hotelList;
    }


}
