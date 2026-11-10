import { useTranslations } from "next-intl";
import { DataTable } from "@/shared/components/data-table";
import { Money } from "@/shared/components/money";
import { TableCell, TableRow } from "@/shared/components/ui/table";
import type { Order } from "@/modules/orders/types";

export function OrderItemsTable({ order }: { order: Order }) {
  const t = useTranslations("orders");
  const head = [t("fields.product"), t("fields.price"), t("fields.quantity"), t("fields.sum")];

  return (
    <DataTable head={head}>
      {order.items.map((item) => (
        <TableRow key={item.productId}>
          <TableCell className="px-4 font-medium">{item.productName}</TableCell>
          <TableCell className="px-4">
            <Money value={item.unitPrice} />
          </TableCell>
          <TableCell className="px-4">{item.quantity}</TableCell>
          <TableCell className="px-4">
            <Money value={item.subtotal} />
          </TableCell>
        </TableRow>
      ))}
      <TableRow>
        <TableCell colSpan={3} className="px-4 text-right font-medium">
          {t("total")}
        </TableCell>
        <TableCell className="px-4 font-semibold">
          <Money value={order.totalAmount} />
        </TableCell>
      </TableRow>
    </DataTable>
  );
}
