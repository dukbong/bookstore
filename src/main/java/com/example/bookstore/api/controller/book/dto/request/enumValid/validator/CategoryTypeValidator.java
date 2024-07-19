package com.example.bookstore.api.controller.book.dto.request.enumValid.validator;

import com.example.bookstore.api.controller.book.dto.request.enumValid.annotation.NotBlankCategoryType;
import com.example.bookstore.domain.category.CategoryType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryTypeValidator implements ConstraintValidator<NotBlankCategoryType, CategoryType> {

    @Override
    public boolean isValid(CategoryType categoryType, ConstraintValidatorContext constraintValidatorContext) {

        if (categoryType == null) {
            return false;
        }

        return true;
    }

}
