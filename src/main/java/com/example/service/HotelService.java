package com.example.service;

import com.example.domain.Hotel;
import com.example.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ホテル情報を操作するクラス.
 */
@Service
@Transactional
public class HotelService {
    @Autowired
    private HotelRepository repository;

    /**
     * 宿泊価格の条件に合うホテル一覧を取得する.
     *
     * @return チーム情報のリスト
     */
    public List<Hotel> getHotelList(final Integer cost) {
        if(cost == null) {
            return  repository.findAll();
        }

        return repository.findByLeqPrice(cost);
    }


}
