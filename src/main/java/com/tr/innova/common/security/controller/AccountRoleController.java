package com.tr.innova.common.security.controller;

import com.tr.innova.common.utils.AppPageResponse;
import com.tr.innova.common.utils.AppResponse;
import com.tr.innova.common.utils.ResponseUtil;
import com.tr.innova.criteria.AccountRoleCriteria;
import com.tr.innova.common.security.exception.BadRequestAlertException;
import com.tr.innova.common.security.model.dto.AccountRoleDto;
import com.tr.innova.common.security.service.AccountRoleService.AccountRoleService;
import com.tr.innova.common.security.service.AccountRoleService.impl.AccountRoleImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class AccountRoleController {

    private final AccountRoleImpl accountRoleImpl;
    private final AccountRoleService accountRoleService;

    public AccountRoleController(AccountRoleImpl accountRoleImpl, AccountRoleService accountRoleService) {
        this.accountRoleImpl = accountRoleImpl;
        this.accountRoleService = accountRoleService;
    }

    /**
     * GET /api/rol/all : Tüm rollerin listesini döner.
     * Bu metod, tüm rol verilerini alır ve bir liste halinde döner.
     *
     * @return ResponseEntity<AppPageResponse < List < AccountRoleDto>>> Tüm rollerin DTO'larını içeren yanıt.
     */
    @GetMapping("/all")
    public ResponseEntity<AppPageResponse<List<AccountRoleDto>>> getAll() {
        List<AccountRoleDto> list = accountRoleImpl.getAll();
        return ResponseUtil.wrapListResponse(list);
    }

    /**
     * GET /api/rol : Roller için kriterlere göre arama yapar ve sayfalı sonuç döner.
     * Bu metod, rol verilerini belirli kriterlere göre filtreler ve sayfalama desteği ile döner.
     *
     * @param accountRoleCriteria Arama kriterlerini içeren nesne.
     * @param pageable     Sayfalama parametreleri.
     * @return ResponseEntity<AppPageResponse < List < AccountRoleDto>>> Sayfalı ve kriterlere göre filtrelenmiş rol verisi.
     */
    @GetMapping("")
    public ResponseEntity<AppPageResponse<List<AccountRoleDto>>> pageableQueryAccountRole(AccountRoleCriteria accountRoleCriteria,
                                                                                          Pageable pageable) {
        Page<AccountRoleDto> page = accountRoleImpl.getAllByCriteria(accountRoleCriteria, pageable);
        return ResponseUtil.wrapPageResponse(page == null ? page = Page.empty() : page);
    }

    /**
     * GET /api/rol/{id} : Belirtilen ID'ye sahip rolü döner.
     * Bu metod, verilen ID'ye sahip rolü arar ve bulunan rolün DTO'sunu döner.
     * Eğer rol bulunamazsa, 404 Not Found yanıtı döner.
     *
     * @param id Rolün ID'si.
     * @return ResponseEntity<AppResponse < AccountRoleDto>> İlgili rolün DTO'su.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppResponse<AccountRoleDto>> getAccountRoleById(@PathVariable Long id) {
        AccountRoleDto accountRoleDto = accountRoleImpl.findById(id).orElse(null);
        return ResponseUtil.wrapDataResponse(accountRoleDto);
    }

    /**
     * POST /api/rol : Yeni bir rol oluşturur.
     * Bu metod, verilen rol DTO'suna göre yeni bir rol kaydı oluşturur. Eğer rol daha önce
     * tanımlanmışsa, bir hata mesajı ile birlikte 400 Bad Request yanıtı döner.
     * İşlem başarılı olursa, oluşturulan rolün DTO'sunu içeren 201 Created yanıtı döner.
     *
     * @param accountRoleDto Oluşturulacak rolün DTO nesnesi.
     * @return ResponseEntity<AppResponse < AccountRoleDto>> Başarılı olduğunda oluşturulan rolün DTO'sunu döner.
     */
    @PostMapping("")
    public ResponseEntity<AppResponse<AccountRoleDto>> createAccountRole(@RequestBody AccountRoleDto accountRoleDto) {
        if (accountRoleDto.getId() != null) {
            throw new BadRequestAlertException("Rol daha önce tanımlanmış!!!");
        }
        accountRoleDto = accountRoleService.save(accountRoleDto);
        return ResponseUtil.wrapDataResponse(accountRoleDto);
    }

    /**
     * DELETE /api/rol/{id} : Belirtilen ID'ye sahip rolü siler (soft delete).
     * Bu metod, belirtilen ID'ye sahip rolün silinmesini (soft delete) sağlar.
     * Eğer rol bulunamazsa, uygun bir hata mesajı döner.
     *
     * @param id Rolün ID'si.
     * @return ResponseEntity<AppResponse < Void>> Başarılı olduğunda 204 No Content yanıtı döner.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<Void>> deleteAccountRole(@PathVariable("id") Long id) {
        accountRoleService.deleteById(id);
        return ResponseUtil.noContentResponse();
    }
}
