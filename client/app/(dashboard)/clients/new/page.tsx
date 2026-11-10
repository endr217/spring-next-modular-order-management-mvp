import { getTranslations } from "next-intl/server";
import { createClient } from "@/modules/clients/actions/create-client";
import { ClientForm } from "@/modules/clients/components/client-form";
import { FormCard } from "@/shared/components/form-card";

export default async function NewClientPage() {
  const t = await getTranslations("clients");

  return (
    <FormCard title={t("new")}>
      <ClientForm action={createClient} />
    </FormCard>
  );
}
