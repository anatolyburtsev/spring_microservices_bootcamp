package ru.java.mentor.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name="Expedia_Data")
@Getter
@Setter
@Slf4j
@NoArgsConstructor
public class ExpediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date date_time;
    private int site_name;
    private int posa_continent;
    private int user_location_country;
    private int user_location_region;
    private int user_location_city;
    private double orig_destination_distance;
    private int user_id;
    private short is_mobile;
    private int is_package;
    private int channel;
    private String srch_ci;
    private String srch_co;
    private int srch_adults_cnt;
    private int srch_children_cnt;
    private int srch_rm_cnt;
    private int srch_destination_id;
    private int srch_destination_type_id;
    private int hotel_continent;
    private int hotel_country;
    private int hotel_market;
    private short is_booking;
    private long cnt;
    private int hotel_cluster;

    public ExpediaData(String[] csvLine){
        this.date_time = parseDate(csvLine[0]);
        try {
            this.site_name = Integer.valueOf(csvLine[1]);
        } catch (NumberFormatException ex) {
            this.site_name = 0;
        }
        this.posa_continent = Integer.valueOf(csvLine[2]);
        this.user_location_country = Integer.valueOf(csvLine[3]);
        this.user_location_region = Integer.valueOf(csvLine[4]);
        this.user_location_city = Integer.valueOf(csvLine[5]);
        this.orig_destination_distance = Double.valueOf(csvLine[6] + "0");
        this.user_id = Integer.valueOf(csvLine[7]);
        this.is_mobile = Short.valueOf(csvLine[8]);
        this.is_package = Integer.valueOf(csvLine[9]);
        this.channel = Integer.valueOf(csvLine[10]);
        this.srch_ci = csvLine[11];
        this.srch_co = csvLine[12];
        this.srch_adults_cnt = Integer.valueOf(csvLine[13]);
        this.srch_children_cnt = Integer.valueOf(csvLine[14]);
        this.srch_rm_cnt = Integer.valueOf(csvLine[15]);
        this.srch_destination_id = Integer.valueOf(csvLine[16]);
        this.srch_destination_type_id = Integer.valueOf(csvLine[17]);
        this.is_booking = Short.valueOf(csvLine[18]);
        this.cnt = Integer.valueOf(csvLine[19]);
        this.hotel_continent = Integer.valueOf(csvLine[20]);
        this.hotel_country = Integer.valueOf(csvLine[21]);
        this.hotel_market = Integer.valueOf(csvLine[22]);
        this.hotel_cluster = Integer.valueOf(csvLine[23]);
    }

    private Date parseDate(String dateString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
        Date result = null;
        try {
            result = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
