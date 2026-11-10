import { getTranslations } from "next-intl/server";
import { LinkButton } from "@/shared/components/link-button";
import { OrderDetails } from "@/modules/orders/components/order-details";
import { OrderItemsTable } from "@/modules/orders/components/order-items-table";
import { StatusActions } from "@/modules/orders/components/status-actions";
import { PageHeader } from "@/shared/components/page-header";
import { getOrder } from "@/modules/orders/queries/get-order";

export async function OrderView({ params }: Pick<PageProps<"/orders/[id]">, "params">) {
  const { id } = await params;
  const [t, order] = await Promise.all([getTranslations("orders"), getOrder(id)]);

  return (
    <>
      <PageHeader title={t("detailTitle", { id: order.id })}>
        <LinkButton href="/orders" variant="outline">
          {t("all")}
        </LinkButton>
      </PageHeader>
      <OrderDetails order={order} />
      <div className="mb-6">
        <StatusActions order={order} />
      </div>
      <h2 className="mb-3 text-lg font-semibold">{t("items")}</h2>
      <OrderItemsTable order={order} />
    </>
  );
}
