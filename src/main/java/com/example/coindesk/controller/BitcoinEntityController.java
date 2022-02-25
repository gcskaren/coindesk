package com.example.coindesk.controller;

import com.example.coindesk.model.BitcoinEntity;
import com.example.coindesk.model.Tools;
import com.example.coindesk.repository.BitcoinEntityRep;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
public class BitcoinEntityController {
    @Autowired
    private BitcoinEntityRep bitcoinEntityRep;

    @Autowired
    private Tools tools;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/v1/bitcoin",produces="application/json;charset=UTF-8")
    public List<BitcoinEntity> getAll(){
        return bitcoinEntityRep.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/v1/bitcoin/{id}",produces="application/json;charset=UTF-8")
    public List<BitcoinEntity> getByID(@PathVariable int id){
        return bitcoinEntityRep.findAllById(Collections.singleton(id));
    }

    @RequestMapping(value="/v1/save",method= RequestMethod.POST)
    public String add(@RequestBody BitcoinEntity bitcoinEntity ){
        bitcoinEntityRep.save(bitcoinEntity);
        return "success";
    }

    @DeleteMapping("/v1/delete/{id}")
    public String deleteBitcoin(@PathVariable int id){
        bitcoinEntityRep.deleteById(id);
        return "success";
    }

    @PutMapping("/v1/modify/{id}")
    public ResponseEntity<BitcoinEntity> update(@PathVariable("id") int id, @RequestBody BitcoinEntity bitcoinEntity){
        Optional<BitcoinEntity> bitcoinEntityData = bitcoinEntityRep.findById(id);
        if (bitcoinEntityData.isPresent()){
            BitcoinEntity _bitcoinEntity = bitcoinEntityData.get();
            _bitcoinEntity.set匯率(bitcoinEntity.get匯率());
            _bitcoinEntity.set幣別(bitcoinEntity.get幣別());
            _bitcoinEntity.set更新時間(bitcoinEntity.get更新時間());
            _bitcoinEntity.set幣別名稱(bitcoinEntity.get幣別名稱());
            _bitcoinEntity.set浮動利率(bitcoinEntity.get浮動利率());
          return new ResponseEntity<>(bitcoinEntityRep.save(_bitcoinEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/v1/data",produces="application/json;charset=UTF-8")
    public void getdata() throws IOException, ParseException {
    tools.getcurrentprice();
    }
}
