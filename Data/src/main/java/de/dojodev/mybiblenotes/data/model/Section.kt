package de.dojodev.mybiblenotes.data.model

import de.dojodev.mybiblenotes.bibleapi.model.section.Section as ServiceSection
import de.dojodev.mybiblenotes.bibleapi.model.section.SectionSummary as ServiceSectionSummary
import de.dojodev.mybiblenotes.database.model.bibleData.Section as DBSection

data class Section(
    var id: String = "",
    var title: String = "",
    var content: String = "",
    var verseCount: Int = -1,
    var firstVerseId: String = "",
    var lastVerseId: String = "",
    var firstVerseOrgId: String = "",
    var lastVerseOrgId: String = "",
    var copyright: String = "",
) {
    override fun toString(): String {
        return this.content
    }

    companion object {

        fun fromServiceSectionSummary(serviceSectionSummary: ServiceSectionSummary): Section {
            return Section(
                serviceSectionSummary.id,
                serviceSectionSummary.title,
                "",
                -1,
                serviceSectionSummary.firstVerseId,
                serviceSectionSummary.lastVerseId,
                serviceSectionSummary.firstVerseOrgId,
                serviceSectionSummary.lastVerseOrgId,
                ""
            )
        }

        fun fromServiceSection(serviceSection: ServiceSection): Section {
            return Section(
                serviceSection.id,
                serviceSection.title,
                serviceSection.content,
                serviceSection.verseCount,
                serviceSection.firstVerseId,
                serviceSection.lastVerseId,
                serviceSection.firstVerseOrgId,
                serviceSection.lastVerseOrgId,
                serviceSection.copyright
            )
        }

        fun fromDbSection(dbSection: DBSection): Section {
            return Section(
                dbSection.id,
                dbSection.title,
                dbSection.content,
                dbSection.verseCount,
                dbSection.firstVerseId,
                dbSection.lastVerseId,
                dbSection.firstVerseOrgId,
                dbSection.lastVerseOrgId,
                dbSection.copyright
            )
        }

        fun toDbSection(section: Section, bibleId: String, bookId: String, chapterId: String): DBSection {
            return DBSection(
                section.id,
                bibleId,
                bookId,
                chapterId,
                section.title,
                section.content,
                section.verseCount,
                section.firstVerseId,
                section.lastVerseId,
                section.firstVerseOrgId,
                section.lastVerseOrgId,
                section.copyright
            )
        }
    }
}
