"use server";

import type { FormState } from "@/shared/forms/form-state";
import { saveClient } from "@/modules/clients/actions/save-client";

export async function updateClient(id: number, _: FormState, formData: FormData) {
  return saveClient(`/clients/${id}`, "PUT", formData);
}
