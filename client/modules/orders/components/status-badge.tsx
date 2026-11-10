import { useTranslations } from "next-intl";
import { Badge } from "@/shared/components/ui/badge";
import type { OrderStatus } from "@/modules/orders/types";

type Variant = React.ComponentProps<typeof Badge>["variant"];

const VARIANTS: Record<OrderStatus, Variant> = {
  NEW: "secondary",
  IN_PROGRESS: "outline",
  COMPLETED: "default",
  CANCELLED: "destructive",
};

export function StatusBadge({ status }: { status: OrderStatus }) {
  const t = useTranslations("orders.status");
  return <Badge variant={VARIANTS[status]}>{t(status)}</Badge>;
}
