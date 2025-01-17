package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdsService;

/**
 * Контроллер для обработки запросов об объявлениях
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;

    /**
     * Метод получает все объявления.
     * @return возвращает ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return new ResponseEntity<>(adsService.getAllAds(), HttpStatus.OK);
    }

    /**
     * Метод добавляет новое объявление.
     *
     * @param properties DTO. Включает title, price и description объявления.
     * @param image      принимает изображение.
     * @return возвращает ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile image) {
        return new ResponseEntity<>(adsService.addAd(properties, image), HttpStatus.CREATED);
    }

    /**
     * Метод получает информацию об объявлении по id.
     *
     * @param id идентификатор объявления в БД.
     * @return возвращает ResponseEntity.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        return new ResponseEntity<>(adsService.getAds(id), HttpStatus.OK);
    }

    /**
     * Метод удаляет объявление по id.
     *
     * @param id идентификатор объявления в БД.
     * @return возвращает ResponseEntity.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        adsService.removeAd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Метод обновляет информацию об объявлении по id.
     *
     * @param id               идентификатор объявления в БД.
     * @param createOrUpdateAd DTO. Включает title, price и description объявления.
     * @return DTO Ad. Включает author, image, pk, price и title объявления.
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return new ResponseEntity<>(adsService.updateAds(id, createOrUpdateAd), HttpStatus.OK);
    }

    /**
     * Метод для получения объявлений пользователя
     * @return возвращает объявления авторизованного пользователя.
     */
    @GetMapping(value = "/me")
    public ResponseEntity<Ads> getAdsMe() {
        return new ResponseEntity<>(adsService.getAdsMe(), HttpStatus.OK);
    }

    /**
     * Метод обновляет картинку объявления по id.
     *
     * @param id   идентификатор объявления в БД.
     * @param file принимает изображение.
     * @return бинарный код изображения.
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                              @RequestParam("image") MultipartFile file) {
        return new ResponseEntity<>(adsService.updateImage(id, file), HttpStatus.OK);
    }
}
