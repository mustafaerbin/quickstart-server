package com.tr.innova.common.security.controller;

import com.tr.innova.common.mapper.AccountUserMapper;
import com.tr.innova.common.utils.AppPageResponse;
import com.tr.innova.common.utils.AppResponse;
import com.tr.innova.common.utils.ResponseUtil;
import com.tr.innova.criteria.AccountUserCriteria;
import com.tr.innova.common.security.exception.BadRequestAlertException;
import com.tr.innova.common.security.model.AccountUser;
import com.tr.innova.common.security.model.dto.AccountUserDto;
import com.tr.innova.common.security.repository.AccountUserRepository;
import com.tr.innova.common.security.service.AccountUserService.AccountUserService;
import com.tr.innova.common.security.service.AccountUserService.impl.AccountUserImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kullanici")
public class AccountUserController {

    private final AccountUserImpl accountUserImpl;
    private final AccountUserRepository accountUserRepository;
    private final AccountUserMapper accountUserMapper;
    private final AccountUserService accountUserService;

    public AccountUserController(AccountUserImpl accountUserImpl, AccountUserRepository accountUserRepository, AccountUserMapper accountUserMapper, AccountUserService accountUserService) {
        this.accountUserImpl = accountUserImpl;
        this.accountUserRepository = accountUserRepository;
        this.accountUserMapper = accountUserMapper;
        this.accountUserService = accountUserService;
    }

    @GetMapping("/all")
    public ResponseEntity<AppPageResponse<List<AccountUserDto>>> getAll() {
        List<AccountUserDto> list = accountUserImpl.getAll();
        return ResponseUtil.wrapListResponse(list);
    }

    @GetMapping("")
    public ResponseEntity<AppPageResponse<List<AccountUserDto>>> pageableQueryAccountUser(AccountUserCriteria accountUserCriteria,
                                                                                          Pageable pageable) {
        Page<AccountUserDto> page = accountUserImpl.getAllByCriteria(accountUserCriteria, pageable);
        return ResponseUtil.wrapPageResponse(page == null ? page = Page.empty() : page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppResponse<AccountUserDto>> getAccountUserById(@PathVariable Long id) {
        AccountUser accountUser = accountUserRepository.findById(id).orElse(null);
        AccountUserDto accountUserDto = accountUserMapper.toDto(accountUser);
        return ResponseUtil.wrapDataResponse(accountUserDto);
    }

    /**
     * POST /api/kullanici : Yeni bir kullanıcı oluşturur.
     * Bu metot, verilen Kullanıcı DTO'suna göre yeni bir kullanıcı kaydı oluşturur.
     * Eğer kullanıcı daha önce tanımlanmışsa, bir hata mesajı ile birlikte 400 Bad Request yanıtı döner.
     * İşlem başarılı olursa, oluşturulan kullanıcının DTO'sunu içeren 201 Created yanıtı döner.
     *
     * @param accountUserDto Oluşturulacak kullanıcının DTO nesnesi.
     * @return ResponseEntity<AppResponse < KullaniciDTO>> Başarılı olduğunda oluşturulan kullanıcnın DTO'sunu döner.
     * @throws BadRequestAlertException Eğer kullanici zaten tanımlanmışsa (ID doluysa) hata fırlatılır.
     */
    @PostMapping("")
    public ResponseEntity<AppResponse<AccountUserDto>> createAccountUser(@Valid @RequestBody AccountUserDto accountUserDto) {
        if (accountUserDto.getId() != null) {
            throw new BadRequestAlertException("Kullanıcı daha önce tanımlanmış!!!");
        }
        accountUserDto = accountUserService.save(accountUserDto);
        return ResponseUtil.wrapDataResponse(accountUserDto);
    }

    /**
     * DELETE /api/kullanici/{id} : Belirtilen ID'ye sahip kullanıcıyı siler (soft delete).
     * Bu metot, belirtilen ID'ye sahip kullanıcnın silinmesini (soft delete) sağlar.
     * Eğer kullanıcı bulunamazsa, uygun bir hata mesajı döner.
     *
     * @param id Kullanıcının ID'si.
     * @return ResponseEntity<AppResponse < Void>> Başarılı olduğunda 204 No Content yanıtı döner.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<Void>> deleteAccountUser(@PathVariable("id") Long id) {
        accountUserService.deleteById(id);
        return ResponseUtil.noContentResponse();
    }

    /**
     * Belirli bir kullaniciyi günceller.
     *
     * @param id             Güncellenecek kullaniciyi tanımlayan UUID.
     * @param accountUserDto Güncellenmiş kullanici verisi.
     * @return Güncellenmiş kullaniciyi içeren yanıt.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<AccountUserDto>> updateUser(@PathVariable("id") Long id,
                                                                  @Valid @RequestBody AccountUserDto accountUserDto) {
        return ResponseUtil.wrapDataResponse(accountUserService.update(accountUserDto));
    }

    @PostMapping("/{id}/updateUserRoles")
    public ResponseEntity<AppResponse<AccountUserDto>> updateUserRoles(@PathVariable("id") Long id,
                                                                       @Valid @RequestBody AccountUserDto request) {
        return ResponseUtil.wrapDataResponse(accountUserService.updateUserRoles(id, request.getRoles()));

    }
}
