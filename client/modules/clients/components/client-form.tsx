"use client";

import { useTranslations } from "next-intl";
import { useActionState } from "react";
import { FormActions } from "@/shared/components/form-actions";
import { FormMessage } from "@/shared/components/form-message";
import { TextField } from "@/shared/components/text-field";
import { FieldGroup } from "@/shared/components/ui/field";
import { EMPTY_FORM_STATE, type FormState } from "@/shared/forms/form-state";
import type { Client } from "@/modules/clients/types";

const FIELDS = [
  { name: "name", label: "nameLong", type: "text" },
  { name: "email", label: "email", type: "email" },
  { name: "phone", label: "phone", type: "tel" },
  { name: "address", label: "address", type: "text" },
] as const;

type Props = {
  action: (prev: FormState, formData: FormData) => Promise<FormState>;
  client?: Client;
};

export function ClientForm({ action, client }: Props) {
  const [state, formAction, pending] = useActionState(action, EMPTY_FORM_STATE);
  const t = useTranslations("clients.fields");

  return (
    <form action={formAction} noValidate>
      <FieldGroup>
        <FormMessage message={state.message} />
        {FIELDS.map((field) => (
          <TextField
            key={field.name}
            name={field.name}
            type={field.type}
            label={t(field.label)}
            defaultValue={state.values?.[field.name] ?? client?.[field.name] ?? ""}
            error={state.errors?.[field.name]}
          />
        ))}
        <FormActions pending={pending} cancelHref="/clients" />
      </FieldGroup>
    </form>
  );
}
