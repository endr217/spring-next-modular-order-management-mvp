import { getTranslations } from "next-intl/server";
import { Suspense } from "react";
import { FormCard } from "@/shared/components/form-card";
import { LoadingText } from "@/shared/components/loading-text";
import { NewOrder } from "@/modules/orders/components/new-order";

export default async function NewOrderPage() {
  const t = await getTranslations("orders");

  return (
    <FormCard title={t("new")}>
      <Suspense fallback={<LoadingText />}>
        <NewOrder />
      </Suspense>
    </FormCard>
  );
}
