"use server";

import { refresh } from "next/cache";
import { toFormState } from "@/shared/forms/to-form-state";
import { apiFetch } from "@/shared/api/api-fetch";
import type { FormState } from "@/shared/forms/form-state";

export async function deleteProduct(id: number): Promise<FormState> {
  try {
    await apiFetch(`/products/${id}`, { method: "DELETE" });
  } catch (error) {
    return toFormState(error);
  }
  refresh();
  return {};
}
