import { useTranslations } from "next-intl";
import { DateTime } from "@/shared/components/date-time";
import { LinkButton } from "@/shared/components/link-button";
import { Money } from "@/shared/components/money";
import { StatusBadge } from "@/modules/orders/components/status-badge";
import { TableCell, TableRow } from "@/shared/components/ui/table";
import type { OrderSummary } from "@/modules/orders/types";

export function OrderRow({ order }: { order: OrderSummary }) {
  const t = useTranslations("orders");

  return (
    <TableRow>
      <TableCell className="px-4 font-medium">#{order.id}</TableCell>
      <TableCell className="px-4">{order.clientName}</TableCell>
      <TableCell className="px-4">
        <DateTime value={order.createdAt} />
      </TableCell>
      <TableCell className="px-4">
        <StatusBadge status={order.status} />
      </TableCell>
      <TableCell className="px-4">
        <Money value={order.totalAmount} />
      </TableCell>
      <TableCell className="px-4 text-right">
        <LinkButton href={`/orders/${order.id}`} variant="ghost" size="sm">
          {t("open")}
        </LinkButton>
      </TableCell>
    </TableRow>
  );
}
