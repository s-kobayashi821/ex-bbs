package com.example.service;

import com.example.domain.Cloth;
import com.example.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 衣類情報を操作するクラス.
 */
@Service
@Transactional
public class ClothService {
    @Autowired
    private ClothRepository repository;

    /**
     * DBに存在する色のリストを取得する.
     *
     * @return 色のリスト(重複なし)
     */
    public List<String> getColorList(){
        List<Cloth> clothList = repository.findAll();
        Set<String> colorSet = new TreeSet<>();
        for(Cloth cloth: clothList){
            colorSet.add(cloth.getColor());
        }
        return  colorSet.stream().toList();
    }


    /**
     * 性別と色が一致する服のリストを返す.
     *
     * @return 服のリスト
     */
    public List<Cloth> getClothByGenderColor(final int gender, final String color) {
            return repository.findByGenderColor(gender, color);
    }
}
