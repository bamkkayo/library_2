"use client";

import { useRouter } from "next/navigation";

export default function DeleteButton({ id }: { id: number }) {
    const router = useRouter();

    const handleDelete = async () => {
        if (!confirm("정말로 이 도서를 삭제하시겠습니까?")) return;

        try {
            const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/books/${id}`, {
                method: "DELETE",
            });

            if (res.ok) {
                alert("삭제되었습니다.");
                router.push("/");
                router.refresh();
            } else {
                alert("삭제 실패");
            }
        } catch (error) {
            alert("에러가 발생했습니다.");
        }
    };

    return (
        <button
            onClick={handleDelete}
            className="flex-1 py-4 bg-red-50 text-red-600 font-bold rounded-2xl hover:bg-red-100 transition"
        >
            도서 삭제
        </button>
    );
}