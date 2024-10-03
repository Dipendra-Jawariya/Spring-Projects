package com.dipendra.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {



    private CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompany() {
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company) {
        boolean bool = companyService.updateCompany(company, id);
        if (!bool) {
            return new ResponseEntity<>("Company Does not exists",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Company updated successfully", HttpStatus.OK        );
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompanyById(id);
        if(!isDeleted) {
            return new ResponseEntity<>("Company Does not exists", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable  Long id) {
        Company company =companyService.getCompanyById(id);
        if(company==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company,HttpStatus.OK);
    }
}
