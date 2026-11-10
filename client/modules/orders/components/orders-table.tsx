import { useTranslations } from "next-intl";
import { DataTable } from "@/shared/components/data-table";
import { EmptyRow } from "@/shared/components/empty-row";
import { OrderRow } from "@/modules/orders/components/order-row";
import type { OrderSummary } from "@/modules/orders/types";

export function OrdersTable({ orders }: { orders: OrderSummary[] }) {
  const t = useTranslations("orders");
  const head = [t("fields.number"), t("fields.client"), t("fields.date"), t("fields.status"), t("fields.sum"), ""];

  return (
    <DataTable head={head}>
      {orders.length === 0 && <EmptyRow colSpan={head.length} text={t("empty")} />}
      {orders.map((order) => (
        <OrderRow key={order.id} order={order} />
      ))}
    </DataTable>
  );
}
