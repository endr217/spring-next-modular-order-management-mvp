import { getTranslations } from "next-intl/server";
import { Suspense } from "react";
import { LinkButton } from "@/shared/components/link-button";
import { LoadingText } from "@/shared/components/loading-text";
import { OrdersList } from "@/modules/orders/components/orders-list";
import { PageHeader } from "@/shared/components/page-header";

export default async function OrdersPage({ searchParams }: PageProps<"/orders">) {
  const t = await getTranslations("orders");

  return (
    <>
      <PageHeader title={t("title")}>
        <LinkButton href="/orders/new">{t("new")}</LinkButton>
      </PageHeader>
      <Suspense fallback={<LoadingText />}>
        <OrdersList searchParams={searchParams} />
      </Suspense>
    </>
  );
}
