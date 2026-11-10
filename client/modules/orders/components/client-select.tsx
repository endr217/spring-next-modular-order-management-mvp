"use client";

import { useTranslations } from "next-intl";
import { useState } from "react";
import { SelectField } from "@/shared/components/select-field";
import type { Client } from "@/modules/clients/types";

export function ClientSelect({ clients, error }: { clients: Client[]; error?: string }) {
  const [clientId, setClientId] = useState("");
  const t = useTranslations("orders");

  return (
    <SelectField
      name="clientId"
      label={t("fields.client")}
      placeholder={t("selectClient")}
      options={clients.map((client) => ({ value: client.id, label: client.name }))}
      value={clientId}
      onChange={(event) => setClientId(event.target.value)}
      error={error}
    />
  );
}
