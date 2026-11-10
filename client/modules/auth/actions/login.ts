"use server";

import { redirect } from "next/navigation";
import { formValues } from "@/shared/forms/form-values";
import { parseForm } from "@/shared/forms/parse-form";
import { toFormState } from "@/shared/forms/to-form-state";
import { apiFetch } from "@/shared/api/api-fetch";
import { saveToken } from "@/shared/auth/session";
import type { FormState } from "@/shared/forms/form-state";
import type { TokenResponse } from "@/modules/auth/types";
import { loginSchema } from "@/modules/auth/schema";

export async function login(_: FormState, formData: FormData): Promise<FormState> {
  const { username = "", password } = formValues(formData);
  const values = { username };
  const parsed = await parseForm(loginSchema, { username, password });
  if (!parsed.success) return { errors: parsed.errors, values };

  try {
    const token = await apiFetch<TokenResponse>("/auth/login", {
      method: "POST",
      body: parsed.data,
      auth: false,
    });
    await saveToken(token.accessToken, token.expiresIn);
  } catch (error) {
    return toFormState(error, values);
  }
  redirect("/orders");
}
