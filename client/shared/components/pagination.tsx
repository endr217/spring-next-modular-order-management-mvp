import Link from "next/link";
import { getTranslations } from "next-intl/server";
import { Button } from "@/shared/components/ui/button";

type Props = {
  page: { number: number; totalPages: number; totalElements: number };
  basePath: string;
  params: Record<string, string>;
};

export async function Pagination({ page, basePath, params }: Props) {
  if (page.totalPages <= 1) return null;
  const t = await getTranslations("common.pagination");

  const href = (index: number) => {
    const search = new URLSearchParams({ ...params, page: String(index + 1) });
    return `${basePath}?${search}`;
  };

  return (
    <nav className="mt-4 flex items-center justify-between text-sm text-muted-foreground" aria-label={t("label")}>
      <span>{t("summary", { page: page.number + 1, totalPages: page.totalPages, total: page.totalElements })}</span>
      <div className="flex gap-2">
        <Button asChild variant="outline" size="sm" aria-disabled={page.number === 0}>
          {page.number === 0 ? <span>{t("previous")}</span> : <Link href={href(page.number - 1)}>{t("previous")}</Link>}
        </Button>
        <Button asChild variant="outline" size="sm" aria-disabled={page.number + 1 >= page.totalPages}>
          {page.number + 1 >= page.totalPages ? (
            <span>{t("next")}</span>
          ) : (
            <Link href={href(page.number + 1)}>{t("next")}</Link>
          )}
        </Button>
      </div>
    </nav>
  );
}
