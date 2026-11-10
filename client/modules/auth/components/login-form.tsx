"use client";

import { useTranslations } from "next-intl";
import { useActionState } from "react";
import { FormMessage } from "@/shared/components/form-message";
import { SubmitButton } from "@/shared/components/submit-button";
import { TextField } from "@/shared/components/text-field";
import { FieldGroup } from "@/shared/components/ui/field";
import { EMPTY_FORM_STATE } from "@/shared/forms/form-state";
import { login } from "@/modules/auth/actions/login";

export function LoginForm() {
  const [state, formAction, pending] = useActionState(login, EMPTY_FORM_STATE);
  const t = useTranslations("login");

  return (
    <form action={formAction} noValidate>
      <FieldGroup>
        <FormMessage message={state.message} />
        <TextField
          name="username"
          label={t("username")}
          autoComplete="username"
          defaultValue={state.values?.username}
          error={state.errors?.username}
        />
        <TextField
          name="password"
          type="password"
          label={t("password")}
          autoComplete="current-password"
          error={state.errors?.password}
        />
        <SubmitButton pending={pending}>{t("submit")}</SubmitButton>
      </FieldGroup>
    </form>
  );
}
