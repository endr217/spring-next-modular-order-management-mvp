"use client";

import { useTranslations } from "next-intl";
import { useActionState } from "react";
import { FormActions } from "@/shared/components/form-actions";
import { FormMessage } from "@/shared/components/form-message";
import { SelectField } from "@/shared/components/select-field";
import { TextField } from "@/shared/components/text-field";
import { FieldGroup } from "@/shared/components/ui/field";
import { EMPTY_FORM_STATE, type FormState } from "@/shared/forms/form-state";
import type { Category, Product } from "@/modules/products/types";

type Props = {
  action: (prev: FormState, formData: FormData) => Promise<FormState>;
  categories: Category[];
  product?: Product;
};

export function ProductForm({ action, categories, product }: Props) {
  const [state, formAction, pending] = useActionState(action, EMPTY_FORM_STATE);
  const t = useTranslations();
  const initial = { name: product?.name, price: product?.price, stock: product?.stock, categoryId: product?.category.id };
  const value = (name: keyof typeof initial) => state.values?.[name] ?? String(initial[name] ?? "");
  const field = (name: keyof typeof initial) => ({ name, defaultValue: value(name), error: state.errors?.[name] });

  return (
    <form action={formAction} noValidate>
      <FieldGroup>
        <FormMessage message={state.message} />
        <TextField label={t("products.fields.name")} {...field("name")} />
        <TextField label={t("products.fields.priceEur")} inputMode="decimal" {...field("price")} />
        <TextField label={t("products.fields.stockLong")} inputMode="numeric" {...field("stock")} />
        <SelectField
          label={t("products.fields.category")}
          placeholder={t("common.select")}
          options={categories.map((category) => ({ value: category.id, label: category.name }))}
          {...field("categoryId")}
        />
        <FormActions pending={pending} cancelHref="/products" />
      </FieldGroup>
    </form>
  );
}
