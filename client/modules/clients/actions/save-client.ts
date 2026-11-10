import "server-only";
import { redirect } from "next/navigation";
import { formValues } from "@/shared/forms/form-values";
import { parseForm } from "@/shared/forms/parse-form";
import { toFormState } from "@/shared/forms/to-form-state";
import { apiFetch } from "@/shared/api/api-fetch";
import type { FormState } from "@/shared/forms/form-state";
import { clientSchema } from "@/modules/clients/schema";

export async function saveClient(path: string, method: "POST" | "PUT", formData: FormData): Promise<FormState> {
  const values = formValues(formData);
  const parsed = await parseForm(clientSchema, values);
  if (!parsed.success) return { errors: parsed.errors, values };

  try {
    await apiFetch(path, { method, body: parsed.data });
  } catch (error) {
    return toFormState(error, values);
  }
  redirect("/clients");
}
