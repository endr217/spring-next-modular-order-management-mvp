"use server";

import type { FormState } from "@/shared/forms/form-state";
import { saveClient } from "@/modules/clients/actions/save-client";

export async function createClient(_: FormState, formData: FormData) {
  return saveClient("/clients", "POST", formData);
}
