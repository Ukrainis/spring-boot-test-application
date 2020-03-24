package com.example.demo.services;

import com.example.demo.entities.Company;
import com.example.demo.entities.User;
import com.example.demo.helpers.Utils;
import com.example.demo.requests.AddCompanyRequest;

import org.springframework.stereotype.Service;


@Service
public class CompanyService {

    public boolean isCompanyInvalid(AddCompanyRequest company){
        return Utils.isEmpty(company.getBs()) || Utils.isEmpty(company.getCatchPhrase())
            || Utils.isEmpty(company.getName());
    }

    public Company makeCompanyFromRequest(AddCompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setCatchPhrase(request.getCatchPhrase());
        company.setBs(request.getBs());

        return company;
    }

    public User updateUserCompany(User user, Company company){
        user.getCompany().setName(company.getName());
        user.getCompany().setCatchPhrase(company.getCatchPhrase());
        user.getCompany().setBs(company.getBs());

        return user;
    }
}