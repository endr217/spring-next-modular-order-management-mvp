"use server";

import { saveProduct } from "@/modules/products/actions/save-product";
import type { FormState } from "@/shared/forms/form-state";

export async function createProduct(_: FormState, formData: FormData) {
  return saveProduct("/products", "POST", formData);
}
