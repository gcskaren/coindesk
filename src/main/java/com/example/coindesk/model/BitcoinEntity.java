package com.example.coindesk.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Table(name = "BITCOIN", schema = "PUBLIC", catalog = "TEST")
public class BitcoinEntity  implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "序號",updatable=false,nullable=false)
    private int 序號;
    @Basic
    @Column(name = "幣別名稱")
    private String 幣別名稱;
    @Basic
    @Column(name = "幣別")
    private String 幣別;
    @Basic
    @Column(name = "匯率")
    private String 匯率;
    @Basic
    @Column(name = "說明")
    private String 說明;
    @Basic
    @Column(name = "浮動利率")
    private String 浮動利率;
    @Basic
    @Column(name = "更新時間")
    private String 更新時間;

    public int get序號() {
        return 序號;
    }

    public void set序號(int 序號) {
        this.序號 = 序號;
    }

    public String get幣別名稱() {
        return 幣別名稱;
    }

    public void set幣別名稱(String 幣別名稱) {
        this.幣別名稱 = 幣別名稱;
    }

    public String get幣別() {
        return 幣別;
    }

    public void set幣別(String 幣別) {
        this.幣別 = 幣別;
    }

    public String get匯率() {
        return 匯率;
    }

    public void set匯率(String 匯率) {
        this.匯率 = 匯率;
    }

    public String get說明() {
        return 說明;
    }

    public void set說明(String 說明) {
        this.說明 = 說明;
    }

    public String get浮動利率() {
        return 浮動利率;
    }

    public void set浮動利率(String 浮動利率) {
        this.浮動利率 = 浮動利率;
    }

    public String get更新時間() {
        return 更新時間;
    }

    public void set更新時間(String 更新時間) {
        this.更新時間 = 更新時間;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitcoinEntity that = (BitcoinEntity) o;
        return 序號 == that.序號 && Objects.equals(幣別名稱, that.幣別名稱) && Objects.equals(幣別, that.幣別) && Objects.equals(匯率, that.匯率) && Objects.equals(說明, that.說明) && Objects.equals(浮動利率, that.浮動利率) && Objects.equals(更新時間, that.更新時間);
    }

    @Override
    public int hashCode() {
        return Objects.hash(序號, 幣別名稱, 幣別, 匯率, 說明, 浮動利率, 更新時間);
    }
}
