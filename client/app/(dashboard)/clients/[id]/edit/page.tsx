import { getTranslations } from "next-intl/server";
import { Suspense } from "react";
import { EditClient } from "@/modules/clients/components/edit-client";
import { FormCard } from "@/shared/components/form-card";
import { LoadingText } from "@/shared/components/loading-text";

export default async function EditClientPage({ params }: PageProps<"/clients/[id]/edit">) {
  const t = await getTranslations("clients");

  return (
    <FormCard title={t("edit")}>
      <Suspense fallback={<LoadingText />}>
        <EditClient params={params} />
      </Suspense>
    </FormCard>
  );
}
